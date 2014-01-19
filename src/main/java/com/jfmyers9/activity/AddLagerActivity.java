package com.jfmyers9.activity;

import android.os.Bundle;

import com.jfmyers9.R;

import roboguice.activity.RoboActivity;

public class AddLagerActivity extends RoboActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle(R.string.add_beer_entry);
        setContentView(R.layout.add_lager_layout);
    }
}
