package com.example.mrinal.techkila;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLTransactionRollbackException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.sql.StatementEvent;
import javax.xml.transform.dom.DOMLocator;

public class SearchResultActivity extends AppCompatActivity implements LocationListener {

    LocationManager locationManager;
    double customerLatitude, customerLongitude;
    ArrayList<String>toDisplayStores;
    int sheetNumber;
    String currentSheet;
    String searchQuery,resultStores="";
    RecyclerView recyclerViewSearchResults;
    LinearLayout waitLinearLayout;
    int noStoreFlag=0;

    public class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(strings[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();

                while (data != -1) {
                    char current = (char) data;
                    result = result + current;
                    data = reader.read();
                }
                return result;
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            waitLinearLayout.setVisibility(View.GONE);
            recyclerViewSearchResults.setVisibility(View.VISIBLE);
            super.onPostExecute(s);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        handleIntent(getIntent());

        recyclerViewSearchResults=findViewById(R.id.recyclerViewSearchResults);
        waitLinearLayout=findViewById(R.id.waitLinearLayout);

        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);

        }

        getLocation();

        //Toast.makeText(this, String.valueOf(customerLatitude)+":"+String.valueOf(customerLongitude), Toast.LENGTH_SHORT).show();

        toDisplayStores=new ArrayList<String>();

        final ParseGeoPoint customerLocation=new ParseGeoPoint(customerLatitude,customerLongitude);
        ParseQuery<ParseUser> query = ParseUser.getQuery();

        query.getInBackground(ParseUser.getCurrentUser().getObjectId().toString(), new GetCallback<ParseUser>() {
            public void done(ParseUser user, ParseException e) {
                if (e == null) {
                    user.put("Location",customerLocation);
                    user.saveInBackground();
                }
                else
                {
                    Toast.makeText(SearchResultActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });


        ParseUser user=ParseUser.getCurrentUser();

        ParseGeoPoint userLocation=(ParseGeoPoint)user.get("Location");
        ParseQuery<ParseUser> nearQuery=ParseUser.getQuery();
        nearQuery.whereNear("Location",userLocation);
        nearQuery.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> list, ParseException e) {

                for(ParseUser parseUser:list)
                {
                    noStoreFlag=0;
                    String currentName=parseUser.getString("StoreName");

                    if(parseUser.getString("StoreName")!=null) {
                        //Toast.makeText(SearchResultActivity.this, currentName, Toast.LENGTH_SHORT).show();

                        switch (currentName)
                        {
                            case "PESU General":
                                sheetNumber=1;
                                break;
                            case "More":
                                sheetNumber=2;
                                break;
                            case "Apollo":
                                sheetNumber=3;
                                break;
                            case "Forum Mall":
                                sheetNumber=4;
                                break;
                                default:
                                    noStoreFlag=1;
                        }

                        if(noStoreFlag==0)
                        {
                        currentSheet = "Sheet" + sheetNumber;
                        DownloadTask downloadTask = new DownloadTask();
                        try {
                            resultStores = (downloadTask.execute("https://script.google.com/macros/s/" +
                                    "AKfycbxOLElujQcy1-ZUer1KgEvK16gkTLUqYftApjNCM_IRTL3HSuDk/" +
                                    "exec?id=1537eUPEsA76XxMM0xo5AqhHmeKh3pJtXkQcSpa4RMXg&sheet=" + currentSheet).get());
                        } catch (Exception exc) {
                        }

                        try {

                            JSONObject jsonObject = new JSONObject(resultStores);
                            String jsonString = jsonObject.getString(currentSheet);
                            JSONArray jsonArray = new JSONArray(jsonString);

                            if (sheetNumber != 4) {

                                for (int j = 0; j < jsonArray.length(); j++) {
                                    JSONObject details = jsonArray.getJSONObject(j);
                                    String itemName = details.getString("Item");

                                    if (searchQuery.equalsIgnoreCase(itemName)) {

                                        toDisplayStores.add(currentName);

                                    }
                                }
                            }
                            else {
                                for (int j = 0; j < jsonArray.length(); j++) {
                                    JSONObject details = jsonArray.getJSONObject(j);
                                    String itemName = details.getString("Brand");

                                    if (searchQuery.equalsIgnoreCase(itemName)) {
                                        toDisplayStores.add(currentName);
                                    }
                                }
                            }


                        } catch (Exception exc) {
                        }

                        recyclerViewSearchResults.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        recyclerViewSearchResults.setAdapter(new AdapterSearchResults(toDisplayStores));

                    }
                    }

                }

                if(toDisplayStores.size()==0)
                {
                    Toast.makeText(SearchResultActivity.this, "Sorry, No Store found!", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public void getLocation()
    {
        try{

            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5, this);

            Location location=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            customerLatitude=Double.valueOf(location.getLatitude());
            customerLongitude=Double.valueOf(location.getLongitude());

        }
        catch (Exception e){}
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent)
    {
        if(Intent.ACTION_SEARCH.equals(intent.getAction()))
        {
            searchQuery=intent.getStringExtra(SearchManager.QUERY);
        }
    }


    @Override
    public void onLocationChanged(Location location) {

        customerLatitude=Double.valueOf(location.getLatitude());
        customerLongitude=Double.valueOf(location.getLongitude());

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

}
