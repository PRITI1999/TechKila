package com.example.mrinal.techkila;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LaunchScreen extends AppCompatActivity {

    public static String typeOfStore,closedDay,addressOfStore, locationAddressText="",timings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_screen);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        CountDownTimer countDownTimer=  new CountDownTimer(1000,1000){

            public void onTick(long millisecondsUntilDone){

                //countdown is counting(every second)
                           }

            public void onFinish(){

                Intent intent=new Intent(getApplicationContext(),SwipeScreen.class);
                startActivity(intent);
                finish();
            }

        };
        countDownTimer.start();

    }
}
