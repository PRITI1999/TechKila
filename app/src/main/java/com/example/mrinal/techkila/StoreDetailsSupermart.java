package com.example.mrinal.techkila;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SearchRecentSuggestionsProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ScrollView;

import com.parse.ParseUser;

public class StoreDetailsSupermart extends AppCompatActivity {

    final int NUM_CATEGORY=4;
    final int NUM_SUBCATEGORY_0=2,NUM_SUBCATEGORY_1=2,NUM_SUBCATEGORY_2=2;
    ScrollView foodAndBeveragesScrollView,foodgrainsOilMasalaSupermartScrollView,beveragesAndSnacksSupermartScrollView;
    ScrollView cleaningAndHouseholdScrollView,liquidCleanersScrollView,brushesScrollView;
    ScrollView kitchenAndAccessoriesScrollView,utensilsScrollView,otherAccessoriesScrollView;
    ScrollView stationaryScrollView;
    Button category[],subcategory0[],subcategory1[],subcategory2[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_details_supermart);

        Intent checkListIntent = getIntent();

        int numberOfCategories=NUM_CATEGORY,ID;
        category=new Button[numberOfCategories];
        subcategory0=new Button[NUM_SUBCATEGORY_0];
        subcategory1=new Button[NUM_SUBCATEGORY_1];
        subcategory2=new Button[NUM_SUBCATEGORY_2];

        String clickedButtonIDCategory,clickedButtonIDSubcategory;

        for(int i=0;i<numberOfCategories;i++)
        {
            clickedButtonIDCategory="category"+String.valueOf(i);
            ID=getResources().getIdentifier(clickedButtonIDCategory, "id", getPackageName());
            category[i]=(Button) findViewById(ID);
        }

        for(int i=0;i<NUM_SUBCATEGORY_0;i++)
        {
            clickedButtonIDSubcategory="category0"+String.valueOf(i);
            ID=getResources().getIdentifier(clickedButtonIDSubcategory, "id", getPackageName());
            subcategory0[i]=(Button)findViewById(ID);
        }

        for(int i=0;i<NUM_SUBCATEGORY_1;i++)
        {
            clickedButtonIDSubcategory="category1"+String.valueOf(i);
            ID=getResources().getIdentifier(clickedButtonIDSubcategory, "id", getPackageName());
            subcategory1[i]=(Button)findViewById(ID);
        }


        for(int i=0;i<NUM_SUBCATEGORY_2;i++)
        {
            clickedButtonIDSubcategory="category2"+String.valueOf(i);
            ID=getResources().getIdentifier(clickedButtonIDSubcategory, "id", getPackageName());
            subcategory2[i]=(Button)findViewById(ID);
        }

        foodAndBeveragesScrollView=(ScrollView)findViewById(R.id.foodAndBeveragesScrollView);
        foodgrainsOilMasalaSupermartScrollView=(ScrollView)findViewById(R.id.foodgrainsOilMasalaSupermartScrollView);
        beveragesAndSnacksSupermartScrollView=(ScrollView)findViewById(R.id.beveragesAndSnacksSupermartScrollView);

        cleaningAndHouseholdScrollView=(ScrollView)findViewById(R.id.cleaningAndHouseholdScrollView);
        liquidCleanersScrollView=(ScrollView)findViewById(R.id.liquidCleansersSupermartScrollView);
        brushesScrollView=(ScrollView)findViewById(R.id.brushesSupermartScrollView);

        kitchenAndAccessoriesScrollView=(ScrollView)findViewById(R.id.kitchenAccessoriesScrollView);
        utensilsScrollView=(ScrollView)findViewById(R.id.utensilsScrollView);
        otherAccessoriesScrollView=(ScrollView)findViewById(R.id.otherAccessoriesScrollView);

        stationaryScrollView=(ScrollView)findViewById(R.id.stationaryScrollView);

    }

    public void submitButtonOnClickSupermart(View view)
    {
        Intent storePageIntent=new Intent(this,StorePage.class);
        startActivity(storePageIntent);
        finish();
    }

    public void skipButtonOnClickSupermart(View view)
    {
        Intent storePageIntent=new Intent(this,StorePage.class);
        startActivity(storePageIntent);
        finish();
    }


    public void showList(View view){

        switch(view.getId())
        {
            case R.id.category0:
                if(foodAndBeveragesScrollView.getVisibility()==View.GONE){

                    category[0].setText("Food and Beverages        <");

                    foodAndBeveragesScrollView.setVisibility(View.VISIBLE);
                }
                else
                {
                    category[0].setText("Food and beverages        >");
                    foodAndBeveragesScrollView.setVisibility(View.GONE);
                }
                break;
            case R.id.category00:
                if(foodgrainsOilMasalaSupermartScrollView.getVisibility()==View.GONE){

                    subcategory0[0].setText("Foodgrains, Oil and Masala                       <");

                    foodgrainsOilMasalaSupermartScrollView.setVisibility(View.VISIBLE);
                }
                else
                {
                    subcategory0[0].setText("Foodgrains, Oil and Masala                       >");
                    foodgrainsOilMasalaSupermartScrollView.setVisibility(View.GONE);
                }
                break;
            case R.id.category01:
                if(beveragesAndSnacksSupermartScrollView.getVisibility()==View.GONE){

                    subcategory0[1].setText("Beverages and Snacks                  <");

                    beveragesAndSnacksSupermartScrollView.setVisibility(View.VISIBLE);
                }
                else
                {
                    subcategory0[1].setText("Beverages and Snacks                  >");
                    beveragesAndSnacksSupermartScrollView.setVisibility(View.GONE);
                }
                break;

            case R.id.category1:
                if(cleaningAndHouseholdScrollView.getVisibility()==View.GONE){

                    category[1].setText("Cleaning and Household        <");

                    cleaningAndHouseholdScrollView.setVisibility(View.VISIBLE);
                }
                else
                {
                    category[1].setText("Cleaning and Hosuehold        >");
                     cleaningAndHouseholdScrollView.setVisibility(View.GONE);
                }
                break;

            case R.id.category10:
                if(liquidCleanersScrollView.getVisibility()==View.GONE){

                    subcategory1[0].setText("Liquid Cleansers         <");

                    liquidCleanersScrollView.setVisibility(View.VISIBLE);
                }
                else
                {
                    subcategory1[0].setText("Liquid Cleansers         >");
                    liquidCleanersScrollView.setVisibility(View.GONE);
                }
                break;
            case R.id.category11:
                if(brushesScrollView.getVisibility()==View.GONE){

                    subcategory1[1].setText("Brushes and accessories        <");

                    brushesScrollView.setVisibility(View.VISIBLE);
                }
                else
                {
                    subcategory1[1].setText("Brushes and accessories        >");
                    brushesScrollView.setVisibility(View.GONE);
                }
                break;

            case R.id.category2:
                if(kitchenAndAccessoriesScrollView.getVisibility()==View.GONE){

                    category[2].setText("Kitchen and accessories     <");

                    kitchenAndAccessoriesScrollView.setVisibility(View.VISIBLE);
                }
                else
                {
                    category[2].setText("Kitchen and accessories     >");
                    kitchenAndAccessoriesScrollView.setVisibility(View.GONE);
                }
                break;

            case R.id.category20:
                if(utensilsScrollView.getVisibility()==View.GONE){

                    subcategory2[0].setText("Utensils        <");

                    utensilsScrollView.setVisibility(View.VISIBLE);
                }
                else
                {
                    subcategory2[0].setText("Utensils        >");
                    utensilsScrollView.setVisibility(View.GONE);
                }
                break;

            case R.id.category21:
                if(otherAccessoriesScrollView.getVisibility()==View.GONE){

                    subcategory2[1].setText("Other accessories      <");

                    otherAccessoriesScrollView.setVisibility(View.VISIBLE);
                }
                else
                {
                    subcategory2[1].setText("Other accessories      >");
                    otherAccessoriesScrollView.setVisibility(View.GONE);
                }
                break;

            case R.id.category3:
                if(stationaryScrollView.getVisibility()==View.GONE){

                    category[3].setText("Stationary     <");
                    stationaryScrollView.setVisibility(View.VISIBLE);
                }
                else
                {
                    category[3].setText("Stationary     >");
                    stationaryScrollView.setVisibility(View.GONE);
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

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(StoreDetailsSupermart.this)
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
