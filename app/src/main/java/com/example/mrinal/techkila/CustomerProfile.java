package com.example.mrinal.techkila;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.parse.ParseUser;

import java.util.ArrayList;

public class CustomerProfile extends AppCompatActivity {

    String nameOfCustomer,usernameOfCustomer,contactCustomer;
    ArrayList<String> displayCustomerProfile;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_profile);
        getIntent();

        displayCustomerProfile=new ArrayList<String>();

        ParseUser customerUser=ParseUser.getCurrentUser();
        nameOfCustomer=customerUser.getString("CustomerName");
        usernameOfCustomer=customerUser.getUsername();
        contactCustomer=customerUser.getString("PhoneNumber");
        displayCustomerProfile.add("Customer Name:\t"+nameOfCustomer);
        displayCustomerProfile.add("Username:\t"+usernameOfCustomer);
        displayCustomerProfile.add("Contact:\t"+contactCustomer);

        recyclerView=findViewById(R.id.recyclerViewCustomerProfile);
        recyclerView.setLayoutManager(new LinearLayoutManager(CustomerProfile.this));
        recyclerView.setAdapter(new Adapter(displayCustomerProfile));
    }
}
