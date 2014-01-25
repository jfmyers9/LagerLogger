package com.jfmyers9.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.jfmyers9.LagerAdapter;
import com.jfmyers9.LagerDatabaseHelper;
import com.jfmyers9.LagerEntry;
import com.jfmyers9.R;
import com.jfmyers9.activity.AddLagerActivity;

import java.util.ArrayList;

import roboguice.fragment.RoboFragment;

public class LagerHistoryFragment extends RoboFragment {
    private ArrayList<LagerEntry> lagerList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_lager_history, container, false);
        LinearLayout interiorLayout = (LinearLayout) rootView.findViewById(R.id.grid_layout);
        LagerDatabaseHelper dbHelper = new LagerDatabaseHelper(getActivity());
        lagerList = dbHelper.getAllLagerEntries();

        GridView lagerGrid = (GridView) rootView.findViewById(R.id.lager_grid);
        lagerGrid.setAdapter(new LagerAdapter(getActivity(), lagerList));

        return rootView;
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

    private void openAddLager() {
        Intent intent = new Intent(getActivity(), AddLagerActivity.class);
        startActivity(intent);
    }
}
