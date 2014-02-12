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
import android.widget.AdapterView;
import android.widget.GridView;

import com.jfmyers9.LagerAdapter;
import com.jfmyers9.LagerDatabaseHelper;
import com.jfmyers9.LagerEntry;
import com.jfmyers9.LagerOpenHelper;
import com.jfmyers9.R;
import com.jfmyers9.activity.AddLagerActivity;
import com.jfmyers9.activity.ViewLagerActivity;

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
        LagerDatabaseHelper dbHelper = new LagerDatabaseHelper(getActivity());
        lagerList = dbHelper.getAllLagerEntries();

        GridView lagerGrid = (GridView) rootView.findViewById(R.id.lager_grid);
        lagerGrid.setAdapter(new LagerAdapter(getActivity(), lagerList));
        lagerGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                LagerAdapter adapter = (LagerAdapter) parent.getAdapter();
                LagerEntry clicked = (LagerEntry) adapter.getItem(position);
                openLagerEntryViewActivity(clicked);
            }
        });

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.lager_history_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
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

    private void openLagerEntryViewActivity(LagerEntry clicked) {
        Intent intent = new Intent(getActivity(), ViewLagerActivity.class);

        Bundle arguments = new Bundle();
        arguments.putString(LagerOpenHelper.COLUMN_NAME, clicked.getName());
        arguments.putString(LagerOpenHelper.COLUMN_RATING, clicked.getRating());
        arguments.putString(LagerOpenHelper.COLUMN_AROMA, clicked.getAroma());
        arguments.putString(LagerOpenHelper.COLUMN_APPEARANCE, clicked.getAppearance());
        arguments.putString(LagerOpenHelper.COLUMN_TASTE, clicked.getTaste());
        arguments.putString(LagerOpenHelper.COLUMN_IMG, clicked.getImage());
        arguments.putLong(LagerOpenHelper.COLUMN_ID, clicked.getId());
        arguments.putString(LagerOpenHelper.COLUMN_CREATED_AT, clicked.getCreatedAt());

        intent.putExtras(arguments);
        startActivity(intent);
    }
}
