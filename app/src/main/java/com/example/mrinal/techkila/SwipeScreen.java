package com.example.mrinal.techkila;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class SwipeScreen extends AppCompatActivity {

    GestureDetectorCompat gestureDetectorCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_screen);
        Intent intent=getIntent();
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        gestureDetectorCompat=new GestureDetectorCompat(this, new LearnGesture());

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureDetectorCompat.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    class LearnGesture extends GestureDetector.SimpleOnGestureListener{

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            if(e2.getX()>e1.getX())
            {

                Intent swipeRight=new Intent(getApplicationContext(),CustomerLogin.class);
                startActivity(swipeRight);


            }
            else if(e2.getX()<e1.getX())
            {

                Intent swipeLeft=new Intent(getApplicationContext(),StoreLogin.class);
                startActivity(swipeLeft);

            }
            return true;
        }
    }
    }

