package com.example.mrinal.techkila;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.ParseUser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class StoreItemDetails extends AppCompatActivity {

   RecyclerView recyclerView;
    ArrayList<String> list;
    String currentSheet;
    int sheetReceived=0;
    int noStoreDataFlag=0;
    LinearLayout linearLayout;

    public class DownloadTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {

            String result="";
            URL url;
            HttpURLConnection urlConnection=null;

            try
            {
                url=new URL(strings[0]);
                urlConnection=(HttpURLConnection)url.openConnection();
                InputStream in=urlConnection.getInputStream();
                InputStreamReader reader=new InputStreamReader(in);
                int data=reader.read();

                while(data!=-1)
                {
                    char current=(char)data;
                    result=result+current;
                    data=reader.read();
                }
                return result;
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONObject jsonObject = new JSONObject(s);
                String jsonString=jsonObject.getString(currentSheet);
                JSONArray  jsonArray=new JSONArray(jsonString);

                if(sheetReceived!=4) {
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject details = jsonArray.getJSONObject(i);
                        String itemName = details.getString("Item");
                        String quantity = details.getString("Quantity");

                        list.add(itemName + ":" + quantity);

                    }
                }
                else
                {
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject details = jsonArray.getJSONObject(i);
                        String brandName = details.getString("Brand");

                        list.add(brandName);

                    }


                }


                recyclerView=findViewById(R.id.recyclerView);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setAdapter(new Adapter(list));
                recyclerView.setVisibility(View.VISIBLE);
                linearLayout.setVisibility(View.GONE);

            }
            catch (Exception e)
            {

            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_item_details);

        linearLayout = findViewById(R.id.waitLinearLayout);

        getIntent();

        switch (String.valueOf(ParseUser.getCurrentUser().get("StoreName")))
        {
            case "PESU General":
                sheetReceived=1;
                break;
            case "More":
                sheetReceived=2;
                break;
            case "Apollo":
                sheetReceived=3;
                break;
            case "Forum Mall":
                sheetReceived=4;
                break;
                default:
                    noStoreDataFlag=1;
                    linearLayout.setVisibility(View.GONE);
                    Toast.makeText(this, "No Store Data found!", Toast.LENGTH_SHORT).show();
        }

        if(noStoreDataFlag==0) {

            list = new ArrayList<String>();
            currentSheet = "Sheet" + sheetReceived;

            DownloadTask task = new DownloadTask();

            task.execute("https://script.google.com/macros/s/" +
                    "AKfycbxOLElujQcy1-ZUer1KgEvK16gkTLUqYftApjNCM_IRTL3HSuDk/" +
                    "exec?id=1537eUPEsA76XxMM0xo5AqhHmeKh3pJtXkQcSpa4RMXg&sheet=" + currentSheet);
        }

    }
}