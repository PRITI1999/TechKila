package com.example.mrinal.techkila;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ScrollView;

import java.util.ArrayList;

public class StoreDetailsGeneral extends AppCompatActivity {

    final int NUM_CATEGORY=4;
    ScrollView foodgrainsOilMasalaScrollView,beveragesSnacksScrollView,cleaningHouseholdScrollView;
    ScrollView beautyHygieneScrollView,bakeryDairyScrollView;
    Button category[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_details_general);
        Intent checkListIntent = getIntent();

        int numberOfCategories=NUM_CATEGORY,ID;
        category=new Button[numberOfCategories];
        String clickedButtonID;
        for(int i=0;i<numberOfCategories;i++) {
            clickedButtonID="category"+String.valueOf(i);
            ID=getResources().getIdentifier(clickedButtonID, "id", getPackageName());
            category[i]=(Button) findViewById(ID);
        }

        foodgrainsOilMasalaScrollView=(ScrollView)findViewById(R.id.foodgrainsOilMasalaScrollView);
        beveragesSnacksScrollView=(ScrollView)findViewById(R.id.beveragesSnacksScrollView);
        cleaningHouseholdScrollView=(ScrollView)findViewById(R.id.cleaningHouseholdScrollView);
        beautyHygieneScrollView=(ScrollView)findViewById(R.id.beautyHygieneScrollView);
        bakeryDairyScrollView=(ScrollView)findViewById(R.id.bakeryDairyScrollView);

    }

    public void showList(View view){

        switch(view.getId())
        {
            case R.id.category0:
                if(foodgrainsOilMasalaScrollView.getVisibility()==View.GONE){

                    category[0].setText("Foodgrains, Oil and Masala            <");

                    foodgrainsOilMasalaScrollView.setVisibility(View.VISIBLE);
                    }
                    else
                        {
                            category[0].setText("Foodgrains, Oil and Masala            >");
                            foodgrainsOilMasalaScrollView.setVisibility(View.GONE);
                        }
                break;
            case R.id.category1:
                if(beveragesSnacksScrollView.getVisibility()==View.GONE){

                    category[1].setText("Beverages and Snacks                       <");

                    beveragesSnacksScrollView.setVisibility(View.VISIBLE);
                }
                else
                {
                    category[1].setText("Beverages and Snacks                       >");
                    beveragesSnacksScrollView.setVisibility(View.GONE);
                }
                break;
            case R.id.category2:
                if(cleaningHouseholdScrollView.getVisibility()==View.GONE){

                    category[2].setText("Cleaning and Household                  <");

                    cleaningHouseholdScrollView.setVisibility(View.VISIBLE);
                }
                else
                {
                    category[2].setText("Cleaning and Household                  >");
                    cleaningHouseholdScrollView.setVisibility(View.GONE);
                }
                break;
            case R.id.category3:
                if(beautyHygieneScrollView.getVisibility()==View.GONE){

                    category[3].setText("Beauty and Hygiene                            <");

                    beautyHygieneScrollView.setVisibility(View.VISIBLE);
                }
                else
                {
                    category[3].setText("Beauty and Hygiene                            >");
                    beautyHygieneScrollView.setVisibility(View.GONE);
                }
                break;
            case R.id.category4:
                if(bakeryDairyScrollView.getVisibility()==View.GONE){

                    category[4].setText("Bakery and Dairy                               <");

                    bakeryDairyScrollView.setVisibility(View.VISIBLE);
                }
                else
                {
                    category[4].setText("Bakery and Dairy                               >");
                    bakeryDairyScrollView.setVisibility(View.GONE);
                }
                break;

        }

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

    public void skipButtonOnClickGeneral(View view)
    {
        Intent storePageIntent=new Intent(this,StorePage.class);
        startActivity(storePageIntent);
        finish();
    }

    public void submitButtonOnClickGeneral(View view)
    {
        Intent storePageIntent=new Intent(this,StorePage.class);
        startActivity(storePageIntent);
        finish();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(StoreDetailsGeneral.this)
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
