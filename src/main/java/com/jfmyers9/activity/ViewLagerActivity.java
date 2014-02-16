package com.jfmyers9.activity;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.RatingBar;

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

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_lager_layout);

        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        prefillLagerInformation(getIntent().getExtras());
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    /* Private Methods */

    private void prefillLagerInformation(Bundle arguments) {
        LagerEntry currentEntry = arguments.getParcelable(LagerHistoryFragment.LAGER_KEY);
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
}