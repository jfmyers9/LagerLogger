package com.jfmyers9.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.google.inject.Inject;
import com.jfmyers9.LagerDatabaseHelper;
import com.jfmyers9.LagerEntry;
import com.jfmyers9.R;
import com.jfmyers9.fragment.LagerHistoryFragment;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;

public class ViewLagerActivity extends RoboActivity {
    @InjectView(R.id.beer_image) private ImageView beerImage;
    @InjectView(R.id.title_entry) private TextView nameText;
    @InjectView(R.id.aroma_entry) private TextView aromaText;
    @InjectView(R.id.appearance_entry) private TextView appearanceText;
    @InjectView(R.id.rate_beer) private RatingBar rating;
    @InjectView(R.id.taste_entry) private TextView tasteText;

    @Inject private LagerDatabaseHelper dbHelper;

    private LagerEntry currentEntry;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_lager_layout);

        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        currentEntry = getIntent().getExtras().getParcelable(LagerHistoryFragment.LAGER_KEY);

        prefillLagerInformation();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.view_lager_menu, menu);
        return true;
    }


    @Override
    protected void onResume() {
        super.onResume();
        refreshCurrentEntry();
        prefillLagerInformation();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.edit_button:
                openEditLager();
                return true;
            case R.id.delete_button:
                destroyCurrentLager();
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    /* Private Methods */

    private void openEditLager() {
        Intent intent = new Intent(this, EditLagerActivity.class);
        intent.putExtra(LagerHistoryFragment.LAGER_KEY, currentEntry);
        startActivity(intent);
    }

    private void prefillLagerInformation() {
        int lagerRating = Integer.parseInt(currentEntry.getRating());

        if (!currentEntry.getImage().isEmpty()) {
            beerImage.setImageURI(Uri.parse(currentEntry.getImage()));
        }
        nameText.setText(currentEntry.getName());
        aromaText.setText(currentEntry.getAroma());
        appearanceText.setText(currentEntry.getAppearance());
        tasteText.setText(currentEntry.getTaste());
        rating.setRating(lagerRating);

        setTitle(currentEntry.getName());
    }

    private void refreshCurrentEntry() {
        currentEntry = dbHelper.getLagerEntryById(currentEntry.getId());
    }

    private void destroyCurrentLager() {
        dbHelper.destroyLagerEntryById(currentEntry.getId());
    }
}