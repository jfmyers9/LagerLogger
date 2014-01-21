package com.jfmyers9.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jfmyers9.LagerDatabaseHelper;
import com.jfmyers9.R;
import com.jfmyers9.activity.AddLagerActivity;

import roboguice.fragment.RoboFragment;

public class LagerHistoryFragment extends RoboFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lager_history, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.lager_history_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.add_lager:
                openAddLager();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onResume() {
        super.onResume();
        LagerDatabaseHelper dbHelper = new LagerDatabaseHelper(getActivity());
        TextView tv = (TextView) getView().findViewById(R.id.history_text);
        tv.setText(dbHelper.getAllLagerEntries().get(0).getName());
    }

    private void openAddLager() {
        Intent intent = new Intent(getActivity(), AddLagerActivity.class);
        startActivity(intent);
    }
}
