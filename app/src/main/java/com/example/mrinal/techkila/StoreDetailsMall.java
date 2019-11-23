package com.example.mrinal.techkila;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckedTextView;

public class StoreDetailsMall extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_details_mall);

        Intent checkListIntent= getIntent();

        int numberOfBrands=20,ID;
        CheckedTextView brand[]=new CheckedTextView[numberOfBrands];
        String checkedTextViewId;
        for(int i=0;i<numberOfBrands;i++) {
            checkedTextViewId="brand"+String.valueOf(i);
            ID=getResources().getIdentifier(checkedTextViewId, "id", getPackageName());
            brand[i]=(CheckedTextView)findViewById(ID);
        }

    }

    public void skipButtonOnClickMall(View view)
    {
        Intent storePageIntent=new Intent(this,StorePage.class);
        startActivity(storePageIntent);
        finish();
    }

    public void submitButtonOnClickMall(View view)
    {
        Intent storePageIntent=new Intent(this,StorePage.class);
        startActivity(storePageIntent);
        finish();

    }
    public void showTick(View view)
    {
        int ID=getResources().getIdentifier(String.valueOf(view.getId()), "id", getPackageName());
        CheckedTextView checkedTextView=(CheckedTextView)findViewById(ID);

        if(checkedTextView.isSelected()){

            checkedTextView.setSelected(false);
            checkedTextView.setCheckMarkDrawable(R.drawable.empty_checkbox);
        }
        else{

            checkedTextView.setSelected(true);
            checkedTextView.setCheckMarkDrawable(R.drawable.checked_image);

        }

    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(StoreDetailsMall.this)
                .setMessage("You can't go back from here")
                .setTitle("Please complete the Sign Up process")
                .setIcon(R.drawable.logo)
                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();
        return;
    }
}
