package com.example.mrinal.techkila;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class StoreDetailsOnTap extends AppCompatActivity {

    ArrayList<String > displayStoreProfile;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_details_on_tap);

        Intent intentStoreTapped=getIntent();
        String storeName=intentStoreTapped.getStringExtra("storeNameTapped");
        ParseQuery<ParseUser> navigateQuery=ParseUser.getQuery();
        navigateQuery.whereEqualTo("StoreName",storeName);
        navigateQuery.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> list, ParseException e) {

                String nameOfStore="",addressOfStore="",dayClosed="",timeOfStore=""
                        ,contactStore="",usernameOfStore="";
                for(ParseUser parseUser:list) {

                    nameOfStore=parseUser.getString("StoreName");
                    addressOfStore=parseUser.getString("AddressOfStore");
                    dayClosed=parseUser.getString("ClosedDay");
                    timeOfStore=parseUser.getString("Timings");
                    contactStore="+91-"+parseUser.getString("PhoneNumber");
                    usernameOfStore=parseUser.getUsername();

                }

                displayStoreProfile=new ArrayList<String>();

                displayStoreProfile.add("Name:\t"+nameOfStore);
                displayStoreProfile.add("Address:\t"+addressOfStore);
                displayStoreProfile.add("Day closed:\t"+dayClosed);
                displayStoreProfile.add("Timings:\t"+timeOfStore);
                displayStoreProfile.add("Contact:\t"+contactStore);

                recyclerView=findViewById(R.id.recyclerViewStoreProfileTap);
                recyclerView.setLayoutManager(new LinearLayoutManager(StoreDetailsOnTap.this));
                recyclerView.setAdapter(new Adapter(displayStoreProfile));
            }
        });
    }
}
