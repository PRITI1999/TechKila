package com.example.mrinal.techkila;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.util.List;
import java.util.Locale;


public class StoreSignUp extends AppCompatActivity implements View.OnClickListener, LocationListener
{

    LocationManager locationManager;
    double locationLatitude,locationLongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_sign_up);
        Intent storeSignUp=getIntent();
        ParseUser.getCurrentUser().logOut();
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.layoutStoreSignUp);
        layout.setOnClickListener(this);

        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);

        }

        getLocation();



    }

    public void getLocation()
    {
        try{

            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5, this);

            Location location=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            locationLatitude=location.getLatitude();
            locationLongitude=location.getLongitude();
        }
        catch (Exception e){}
    }

    public void storeLoginOnClickTextView(View view){

        TextView login=(TextView)findViewById(R.id.storeLogin);
        login.setTextColor(Color.parseColor("#993399"));
        Intent storeLogin=new Intent(this, StoreLogin.class);
        startActivity(storeLogin);
        finish();

    }

    public void storeSignUpOnClickButton(View view){


            EditText nameEditText = (EditText) findViewById(R.id.storeName);
            EditText phoneEditText = (EditText) findViewById(R.id.storePhone);
            EditText usernameEditText = (EditText) findViewById(R.id.storeSignUpUsername);
            EditText passwordEditText = (EditText) findViewById(R.id.storeSignUpPassword);

            final String nameOfStore = nameEditText.getText().toString();
            String username = usernameEditText.getText().toString();
            long phone = Long.parseLong(phoneEditText.getText().toString());
            String password = passwordEditText.getText().toString();

            final ParseUser newUser = new ParseUser();
            newUser.put("StoreName", nameOfStore);
            newUser.setUsername(username);
            newUser.setPassword(password);
            newUser.put("PhoneNumber", String.valueOf(phone));
            ParseGeoPoint location=new ParseGeoPoint(locationLatitude,locationLongitude);
            newUser.put("Location",location);
            newUser.put("TypeOfStore","undefined");
            newUser.put("AddressOfStore","undefined");
            newUser.put("ClosedDay","undefined");
            newUser.put("Timings","undefined");

            newUser.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {

                if(e!=null){

                    new AlertDialog.Builder(StoreSignUp.this)
                            .setMessage(e.toString()+"\nTry again.")
                            .setTitle("Sign Up Failed")
                            .setIcon(R.drawable.logo)
                            .setPositiveButton("Okay", new DialogInterface.OnClickListener(){
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            }).show();

                }
                else
                {
                    Toast.makeText(StoreSignUp.this, "Sign Up successful!", Toast.LENGTH_LONG).show();
                    Intent storeDetailsIntent=new Intent(getApplicationContext(),StoreDetails.class);
                    startActivity(storeDetailsIntent);
                    finish();
                }

            }
        });


    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.layoutStoreSignUp) {

            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

        }
    }

    @Override
    public void onLocationChanged(Location location) {

        locationLatitude=location.getLatitude();
        locationLongitude=location.getLongitude();

        try {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            LaunchScreen.locationAddressText+= "\n"+addresses.get(0).getAddressLine(0)+", "+
                    addresses.get(0).getAddressLine(1)+", "+addresses.get(0).getAddressLine(2);
        }catch(Exception e)
        {

        }


    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

        Toast.makeText(StoreSignUp.this, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();

    }
}
