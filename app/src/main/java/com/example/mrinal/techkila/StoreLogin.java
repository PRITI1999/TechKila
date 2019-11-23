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
import com.parse.ParseObject;
import com.parse.ParseUser;

public class StoreLogin extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_login);
        Intent swipeLeft=getIntent();
        ParseUser.getCurrentUser().logOut();
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.layoutStoreLogin);
        layout.setOnClickListener(this);


    }

    public void alertDialog(String alertMessage){

        new AlertDialog.Builder(StoreLogin.this)
                .setMessage(alertMessage)
                .setTitle("Login Failed")
                .setIcon(R.drawable.logo)
                .setPositiveButton("Okay", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();

    }

    public void storeSignUpOnClickTextView(View view)
    {
        TextView signUp=(TextView)findViewById(R.id.storeSignUp);
        signUp.setTextColor(Color.parseColor("#993399"));
        Intent customerSignUp=new Intent(this, StoreSignUp.class);
        startActivity(customerSignUp);
        finish();
    }

    public void storeLoginOnClickButton(View view){

        EditText usernameEditText=(EditText)findViewById(R.id.storeLoginUsername) ;
        EditText passwordEditText=(EditText)findViewById(R.id.storeLoginPassword);

        final String username=usernameEditText.getText().toString();
        String password=passwordEditText.getText().toString();

        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser parseUser, ParseException e) {

                if(parseUser==null){

                  alertDialog(e.toString()+"\nTry again.");
                }
                else if(parseUser.get("CustomerName")!=null){

                    alertDialog("Oops! That's a customer.\nPlease try again with store credentials.");

                }

                else if(e!=null)
                {
                    alertDialog("Exception!\n");
                }
                else
                {
                    Toast.makeText(StoreLogin.this, "Login succesful!", Toast.LENGTH_LONG).show();
                    Intent storeLoginPageIntent;
                    storeLoginPageIntent = new Intent(StoreLogin.this,StorePage.class);
                    startActivity(storeLoginPageIntent);
                    finish();
                }

            }
        });

    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.layoutStoreLogin) {

            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

        }
    }
}
