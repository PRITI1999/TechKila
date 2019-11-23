package com.example.mrinal.techkila;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class CustomerLogin extends AppCompatActivity implements View.OnClickListener {

    public void alertDialog(String alertMessage){

        new AlertDialog.Builder(CustomerLogin.this)
                .setMessage(alertMessage)
                .setTitle("Login Failed")
                .setIcon(R.drawable.logo)
                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);
        Intent swipeRight = getIntent();
        ParseUser.getCurrentUser().logOut();
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.layoutCustomerLogin);
        layout.setOnClickListener(this);


    }

    public void customerSignUpOnClickTextView(View view) {
        TextView signUp = (TextView) findViewById(R.id.customerSignUp);
        signUp.setTextColor(Color.parseColor("#993399"));
        Intent customerSignUp = new Intent(this, CustomerSignUp.class);
        startActivity(customerSignUp);
        finish();
    }

    public void customerLoginOnClickButton(View view) {

        EditText usernameEditText = (EditText) findViewById(R.id.customerLoginUsername);
        EditText passwordEditText = (EditText) findViewById(R.id.customerLoginPassword);

        final String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser parseUser, ParseException e) {

                if (parseUser == null) {

                    alertDialog(e.toString()+"\nTry again.");


                }
                else if(parseUser.get("StoreName")!=null){

                    alertDialog("Oops! That's a store.\nPlease try again with customer credentials.");

                }
                else {
                    Toast.makeText(CustomerLogin.this, "Login succesful!", Toast.LENGTH_LONG).show();
                    Intent customerPageIntent=new Intent(getApplicationContext(),CustomerPage.class);
                    startActivity(customerPageIntent);
                    finish();
                }

            }
        });

    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.layoutCustomerLogin) {

            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

        }
    }
}

