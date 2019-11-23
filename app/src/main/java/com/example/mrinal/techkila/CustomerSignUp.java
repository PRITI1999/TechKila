package com.example.mrinal.techkila;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class CustomerSignUp extends AppCompatActivity implements View.OnClickListener {

    EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_sign_up);
        Intent customerSignUp = getIntent();
        ParseUser.getCurrentUser().logOut();
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.layoutCustomerSignUp);
        layout.setOnClickListener(this);

    }


    public void alertDialog(String alertMessage) {

        new AlertDialog.Builder(CustomerSignUp.this)
                .setMessage(alertMessage)
                .setTitle("Login Failed")
                .setIcon(R.drawable.logo)
                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();

    }

    public void customerLoginOnClickTextView(View view) {

        TextView login = (TextView) findViewById(R.id.customerLogin);
        login.setTextColor(Color.parseColor("#993399"));
        Intent customerLogin = new Intent(this, CustomerLogin.class);
        startActivity(customerLogin);
        finish();

    }

    public void customerSignUpOnClickButton(View view) {


        EditText nameEditText = (EditText) findViewById(R.id.customerName);
        EditText phoneEditText = (EditText) findViewById(R.id.customerPhone);
        EditText usernameEditText = (EditText) findViewById(R.id.customerSignUpUsername);
        passwordEditText = (EditText) findViewById(R.id.customerSignUpPassword);

        String name = nameEditText.getText().toString();
        String username = usernameEditText.getText().toString();
        long phone = Long.parseLong(phoneEditText.getText().toString());
        String password = passwordEditText.getText().toString();
        final ParseUser newCustomer = new ParseUser();
        newCustomer.put("CustomerName", name);
        newCustomer.setUsername(username);
        newCustomer.setPassword(password);
        newCustomer.put("PhoneNumber", String.valueOf(phone));


        newCustomer.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                ParseQuery<ParseUser> query = new ParseQuery<ParseUser>("ParseUser.class");

                if (e != null) {

                    alertDialog(e.toString() + "\nTry again.");
                } else if (query.equals(newCustomer.get("PhoneNumber"))) {

                    alertDialog("Equal PhoneNumber");
                } else {
                    Toast.makeText(CustomerSignUp.this, "Sign Up successfull!", Toast.LENGTH_LONG).show();
                    Intent customerPageIntent=new Intent(getApplicationContext(),CustomerPage.class);
                    startActivity(customerPageIntent);
                    finish();
                }

            }
        });


    }

   @Override
    public void onClick(View view) {

        if (view.getId() == R.id.layoutCustomerSignUp) {

            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

        }
    }
}