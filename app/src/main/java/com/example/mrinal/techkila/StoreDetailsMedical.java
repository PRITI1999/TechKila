package com.example.mrinal.techkila;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckedTextView;

public class StoreDetailsMedical extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_details_medical);

        Intent checkListIntent=getIntent();

        int numberOfMedicines=20,ID;
        CheckedTextView medicine[]=new CheckedTextView[numberOfMedicines];
        String checkedTextViewId;
        for(int i=0;i<numberOfMedicines;i++) {
            checkedTextViewId="medicine"+String.valueOf(i);
            ID=getResources().getIdentifier(checkedTextViewId, "id", getPackageName());
            medicine[i]=(CheckedTextView)findViewById(ID);
        }
    }

    public void skipButtonOnClickMedical(View view)
    {
        Intent storePageIntent=new Intent(this,StorePage.class);
        startActivity(storePageIntent);
        finish();
    }

    public void submitButtonOnClickMedical(View view)
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
        new AlertDialog.Builder(StoreDetailsMedical.this)
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
