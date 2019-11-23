package com.example.mrinal.techkila;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseQuery;
import com.parse.ParseSession;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class AdapterSearchResults extends RecyclerView.Adapter<AdapterSearchResults.ViewHolder> implements LocationListener {

    private Context context;
    LocationManager locationManager;
    double customerLatitude, customerLongitude;

    private ArrayList<String> data;
    public AdapterSearchResults(ArrayList<String> data){

        this.data=data;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        View view=inflater.inflate(R.layout.list_item_search_layout,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {

        final String title=data.get(i);
        viewHolder.textView.setText(title);

        viewHolder.storeDetailsTap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentStoreNameTapped=new Intent(context,StoreDetailsOnTap.class);
                intentStoreNameTapped.putExtra("storeNameTapped",title);
                context.startActivity(intentStoreNameTapped);

            }
        });

        viewHolder.cardViewNavigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getLocation();

                ParseQuery<ParseUser> navigateQuery=ParseUser.getQuery();
                navigateQuery.whereEqualTo("StoreName",data.get(i));
                navigateQuery.findInBackground(new FindCallback<ParseUser>() {
                    @Override
                    public void done(List<ParseUser> list, ParseException e) {

                        double latitude=0,longitude=0;

                        for(ParseUser parseUser:list){

                            ParseGeoPoint storeGeoPoint =parseUser.getParseGeoPoint("Location");
                            latitude=storeGeoPoint.getLatitude();
                            longitude=storeGeoPoint.getLongitude();

                        }

                        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                                Uri.parse("http://maps.google.com/maps?saddr="+customerLatitude+"," +
                                        customerLongitude+"&daddr="+String.valueOf(latitude)+","
                                        +String.valueOf(longitude)));
                        context.startActivity(intent);

                    }
                });

            }
        });

    }

    public void getLocation()
    {
        try{

            locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5, this);

            Location location=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            customerLatitude=Double.valueOf(location.getLatitude());
            customerLongitude=Double.valueOf(location.getLongitude());

        }
        catch (Exception e){}
    }

    @Override
    public int getItemCount() {
        return data.size();
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

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textView,storeDetailsTap;
        CardView cardViewNavigate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            context=itemView.getContext();

            textView = itemView.findViewById(R.id.textView);
            cardViewNavigate=itemView.findViewById(R.id.cardViewNavigate);
            storeDetailsTap=itemView.findViewById(R.id.storeDetailsTap);

        }

    }

}