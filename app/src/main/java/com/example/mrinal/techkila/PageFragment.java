package com.example.mrinal.techkila;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.view.GestureDetectorCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PageFragment extends android.support.v4.app.Fragment implements View.OnClickListener {



    TextView questions, numberOfPages, textView, fragmentSwipeTextView;
    EditText editText;
    Button submitButton;
    Spinner typeOfStoreSpinner,closedDaySpinner, fromhh,frommm, fromampm, tohh, tomm, toampm;
    int pageNo;
    String fromhhString, frommmString, tohhString, tommString, fromampmString, toampmString;


    public PageFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_page,container,false);
        questions=(TextView)view.findViewById(R.id.questionTextView);
        textView=(TextView)view.findViewById(R.id.textView);
        numberOfPages=(TextView)view.findViewById(R.id.numberOfPagesTextView);
        Bundle bundle=getArguments();
        submitButton=(Button)view.findViewById(R.id.submitButton);
        submitButton.setOnClickListener(this);
        pageNo=bundle.getInt("count");
        String message=Integer.toString(pageNo);
        editText=(EditText)view.findViewById(R.id.editText);
        typeOfStoreSpinner=(Spinner)view.findViewById(R.id.typeOfStoreSpinner);
        closedDaySpinner=(Spinner)view.findViewById(R.id.closedDaySpinner);
        fromhh=(Spinner)view.findViewById(R.id.fromhh);
        frommm=(Spinner)view.findViewById(R.id.frommm);
        fromampm=(Spinner)view.findViewById(R.id.fromampm);
        tohh=(Spinner)view.findViewById(R.id.tohh);
        tomm=(Spinner)view.findViewById(R.id.tomm);
        toampm=(Spinner)view.findViewById(R.id.toampm);
        fragmentSwipeTextView = (TextView)view.findViewById(R.id.fragmentSwipeTextView);
        numberOfPages.setText(message+"/4");
        String questionArray[]={"What kind of store is this?",
                "Enter the complete address of your store?",
                "Select the day when the store remains closed",
                "Enter store timings"};
        questions.setText(questionArray[pageNo-1]);
        ArrayAdapter<String> arrayAdapter;
        editText.setText(LaunchScreen.locationAddressText);

        typeOfStoreSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                LaunchScreen.typeOfStore=String.valueOf(typeOfStoreSpinner.getSelectedItem());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        closedDaySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                LaunchScreen.closedDay=String.valueOf(closedDaySpinner.getSelectedItem());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        if(pageNo==2)
        {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                LaunchScreen.addressOfStore=String.valueOf(editText.getText());

            }

            @Override
            public void afterTextChanged(Editable editable) {

                LaunchScreen.addressOfStore=String.valueOf(editText.getText());


            }
        });
        }

        switch (pageNo) {
            case 1:
                editText.setVisibility(View.INVISIBLE);
                submitButton.setVisibility(View.INVISIBLE);
                ArrayList<String> storeTypes = new ArrayList<String>();
                storeTypes.add("Malls");
                storeTypes.add("Medical");
                storeTypes.add("Supermarket");
                storeTypes.add("General");
                arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, storeTypes);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                typeOfStoreSpinner.setAdapter(arrayAdapter);
                break;
            case 2:
                submitButton.setVisibility(View.INVISIBLE);
                typeOfStoreSpinner.setVisibility(View.INVISIBLE);
                break;
            case 3:
                submitButton.setVisibility(View.INVISIBLE);
                editText.setVisibility(View.INVISIBLE);
                typeOfStoreSpinner.setVisibility(View.INVISIBLE);
                closedDaySpinner.setVisibility(View.VISIBLE);
                ArrayList<String> daysOfWeek = new ArrayList<String>();
                daysOfWeek.add("Sunday");
                daysOfWeek.add("Monday");
                daysOfWeek.add("Tuesday");
                daysOfWeek.add("Wednesday");
                daysOfWeek.add("Thursday");
                daysOfWeek.add("Friday");
                daysOfWeek.add("Saturday");
                arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, daysOfWeek);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                closedDaySpinner.setAdapter(arrayAdapter);
                break;
            case 4:
                editText.setVisibility(View.INVISIBLE);
                typeOfStoreSpinner.setVisibility(View.INVISIBLE);
                fromhh.setVisibility(View.VISIBLE);
                frommm.setVisibility(View.VISIBLE);
                fromampm.setVisibility(View.VISIBLE);
                tohh.setVisibility(View.VISIBLE);
                tomm.setVisibility(View.VISIBLE);
                toampm.setVisibility(View.VISIBLE);
                textView.setVisibility(View.VISIBLE);
                submitButton.setVisibility(View.VISIBLE);
                fragmentSwipeTextView.setVisibility(View.INVISIBLE);
                ArrayList<String> hours = new ArrayList<String>();
                hours.add("01");
                hours.add("02");
                hours.add("03");
                hours.add("04");
                hours.add("05");
                hours.add("06");
                hours.add("07");
                hours.add("08");
                hours.add("09");
                hours.add("10");
                hours.add("11");
                hours.add("12");
                ArrayList<String> minutes = new ArrayList<String>();
                minutes.add("00");
                minutes.add("01");
                minutes.add("02");
                minutes.add("03");
                minutes.add("04");
                minutes.add("05");
                minutes.add("06");
                minutes.add("07");
                minutes.add("08");
                minutes.add("09");
                for (int i = 10; i <= 59; i++) {
                    minutes.add(String.valueOf(i));
                }
                ArrayList<String> ampm = new ArrayList<String>();
                ampm.add("AM");
                ampm.add("PM");
                ArrayAdapter<String> hourArrayAdapter = new ArrayAdapter<String>(getContext(),
                        android.R.layout.simple_list_item_1, hours);
                ArrayAdapter<String> minuteArrayAdapter = new ArrayAdapter<String>(getContext(),
                        android.R.layout.simple_list_item_1, minutes);
                ArrayAdapter<String> ampmArrayAdapter = new ArrayAdapter<String>(getContext(),
                        android.R.layout.simple_list_item_1, ampm);
                hourArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                fromhh.setAdapter(hourArrayAdapter);
                tohh.setAdapter(hourArrayAdapter);
                minuteArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                frommm.setAdapter(minuteArrayAdapter);
                tomm.setAdapter(minuteArrayAdapter);
                ampmArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                fromampm.setAdapter(ampmArrayAdapter);
                toampm.setAdapter(ampmArrayAdapter);

                break;
        }
        return view;
    }




    @Override
    public void onClick(View view) {

        fromhhString=String.valueOf(fromhh.getSelectedItem());
        frommmString=String.valueOf(frommm.getSelectedItem());
        fromampmString=String.valueOf(fromampm.getSelectedItem());
        tohhString=String.valueOf(tohh.getSelectedItem());
        tommString=String.valueOf(tomm.getSelectedItem());
        toampmString=String.valueOf(toampm.getSelectedItem());

        LaunchScreen.timings=fromhhString+":"+frommmString+" "+fromampmString+" - "+
                tohhString+":"+tommString+" "+toampmString;

        ParseUser user=ParseUser.getCurrentUser();
        user.put("TypeOfStore",LaunchScreen.typeOfStore);
        user.put("AddressOfStore",LaunchScreen.addressOfStore);
        user.put("Timings",LaunchScreen.timings);
        user.put("ClosedDay",LaunchScreen.closedDay);

        user.saveInBackground();

        Intent checkListIntent;

                    if(LaunchScreen.typeOfStore.equals("Malls")) {

                     checkListIntent = new Intent(getActivity(), StoreDetailsMall.class);
                     startActivity(checkListIntent);

            }
            else if(LaunchScreen.typeOfStore.equals("General")) {

                     checkListIntent = new Intent(getActivity(), StoreDetailsGeneral.class);
                     startActivity(checkListIntent);

            }
            else if(LaunchScreen.typeOfStore.equals("Medical")){

                        checkListIntent = new Intent(getActivity(), StoreDetailsMedical.class);
                       startActivity(checkListIntent);

                    }
            else if(LaunchScreen.typeOfStore.equals("Supermarket")){

                        checkListIntent=new Intent(getActivity(), StoreDetailsSupermart.class);
                        startActivity(checkListIntent);

                    }

            getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
            }
    }


