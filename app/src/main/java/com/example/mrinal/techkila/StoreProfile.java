package com.example.mrinal.techkila;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.parse.ParseUser;

import java.util.ArrayList;

public class StoreProfile extends AppCompatActivity {

    String nameOfStore,addressOfStore,dayClosed,timeOfStore,contactStore,usernameOfStore;
    ArrayList<String> displayStoreProfile;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_profile);
        getIntent();

        ParseUser storeUser=ParseUser.getCurrentUser();
        nameOfStore=storeUser.getString("StoreName");
        addressOfStore=storeUser.getString("AddressOfStore");
        dayClosed=storeUser.getString("ClosedDay");
        timeOfStore=storeUser.getString("Timings");
        contactStore="+91-"+storeUser.getString("PhoneNumber");
        usernameOfStore=storeUser.getUsername();

        displayStoreProfile=new ArrayList<String>();

        displayStoreProfile.add("Name:\t"+nameOfStore);
        displayStoreProfile.add("Username:\t"+usernameOfStore);
        displayStoreProfile.add("Address:\t"+addressOfStore);
        displayStoreProfile.add("Day closed:\t"+dayClosed);
        displayStoreProfile.add("Timings:\t"+timeOfStore);
        displayStoreProfile.add("Contact:\t"+contactStore);

        recyclerView=findViewById(R.id.recyclerViewStoreProfile);
        recyclerView.setLayoutManager(new LinearLayoutManager(StoreProfile.this));
        recyclerView.setAdapter(new Adapter(displayStoreProfile));

    }
}
