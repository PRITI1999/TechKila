package com.example.mrinal.techkila;

import android.app.ActionBar;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.parse.ParseUser;

public class CustomerPage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public DrawerLayout customerDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_page);

        Intent customerPageIntent=getIntent();

        Toast.makeText(this, "Hello "+String.valueOf(ParseUser.getCurrentUser().get("CustomerName")), Toast.LENGTH_SHORT).show();

        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        android.support.v7.widget.Toolbar toolbar=findViewById(R.id.customer_page_toolbar);
        setSupportActionBar(toolbar);

        android.support.v7.app.ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);

        customerDrawerLayout=findViewById(R.id.customer_drawer_layout);
        NavigationView navigationView=findViewById(R.id.customer_nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                customerDrawerLayout.closeDrawers();

                return false;
            }
        });
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case android.R.id.home:
                customerDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void itemSearchOnClick(View view)
    {
        Intent searchIntent=new Intent(this,ItemSearchPage.class);
        startActivity(searchIntent);
    }
    public void storeSearchOnClick(View view)
    {
        Intent searchIntent2=new Intent(this,StoreSearchPage.class);
        startActivity(searchIntent2);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int itemClickedID=item.getItemId();
        switch(itemClickedID)
        {
            case R.id.customer_profile:

                Intent customerProfileIntent=new Intent(CustomerPage.this,CustomerProfile.class);
                startActivity(customerProfileIntent);
                return true;
            case R.id.customer_home:

                //Toast.makeText(CustomerPage.this, "Home clicked", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.customer_logout:
                ParseUser.getCurrentUser().logOut();
                Intent swipeScreenIntent=new Intent(CustomerPage.this,SwipeScreen.class);
                startActivity(swipeScreenIntent);
                finish();
                return true;
        }
     return false;
    }
}
