package com.example.mrinal.techkila;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;


public class StorePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {



   public  DrawerLayout storeDrawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_page);
        Intent storePageIntent = getIntent();

       // Toast.makeText(this, LaunchScreen.typeOfStore+"+"+LaunchScreen.addressOfStore+"+"+LaunchScreen.closedDay, Toast.LENGTH_SHORT).show();

       // Toast.makeText(StorePage.this, "Hello " + String.valueOf(ParseUser.getCurrentUser().get("StoreName")), Toast.LENGTH_SHORT).show();

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.store_page_toolbar);
        setSupportActionBar(toolbar);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);

        storeDrawerLayout = findViewById(R.id.store_drawer_layout);
        NavigationView navigationView = findViewById(R.id.store_nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Toast.makeText(this, LaunchScreen.typeOfStore, Toast.LENGTH_SHORT).show();

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case android.R.id.home:
                storeDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        item.setChecked(true);
        storeDrawerLayout.closeDrawers();

        int itemClickedID=item.getItemId();
        switch(itemClickedID)
        {

            case R.id.store_profile:

                Intent intentProfile=new Intent(StorePage.this,StoreProfile.class);
                startActivity(intentProfile);

                //Toast.makeText(StorePage.this, "Profile Clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.store_home:

                //Toast.makeText(StorePage.this, "Home clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.store_items:
                Intent intent=new Intent(StorePage.this, StoreItemDetails.class);
                startActivity(intent);

                //Toast.makeText(StorePage.this,"Item", Toast.LENGTH_SHORT).show();
                return true;


            case R.id.store_logout:
                ParseUser.getCurrentUser().logOut();
                Intent swipeScreenIntent=new Intent(StorePage.this,SwipeScreen.class);
                startActivity(swipeScreenIntent);
                finish();
                return true;


        }
        return false;
    }

}
