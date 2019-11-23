package com.example.mrinal.techkila;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;




public class SwipeAdapter extends FragmentStatePagerAdapter {

    public SwipeAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment=new PageFragment();
        Bundle bundle=new Bundle();
        bundle.putInt("count",position+1);
        fragment.setArguments(bundle);
        return fragment;

    }

    @Override
    public int getCount() {
        return 4;
    }
}
