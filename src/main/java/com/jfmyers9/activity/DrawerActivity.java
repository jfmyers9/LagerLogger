package com.jfmyers9.activity;

import android.os.Bundle;
import com.jfmyers9.R;

import roboguice.activity.RoboFragmentActivity;

public class DrawerActivity extends RoboFragmentActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.drawer_layout);
  }
}
