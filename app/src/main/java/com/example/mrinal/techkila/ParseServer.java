package com.example.mrinal.techkila;

import android.app.Application;
import com.parse.Parse;


public class ParseServer extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                .applicationId("882fa2d2793fba3e9bfe272a7efefb2076ab560b")
                .clientKey("3bb1092194afbd0886e7ffcb2bb22d06e06dc34b")
                .server("http://18.217.94.204:80/parse/")
                .build()
        );

    }
}
