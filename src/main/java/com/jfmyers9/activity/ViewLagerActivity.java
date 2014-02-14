package com.jfmyers9.activity;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.jfmyers9.LagerOpenHelper;
import com.jfmyers9.R;

public class ViewLagerActivity extends Activity {
    private ImageView beerImage;
    private TextView nameText;
    private TextView aromaText;
    private TextView appearanceText;
    private RatingBar rating;
    private TextView tasteText;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_lager_layout);

        beerImage = (ImageView) findViewById(R.id.beer_image);
        nameText = (TextView) findViewById(R.id.title_entry);
        aromaText = (TextView) findViewById(R.id.aroma_entry);
        appearanceText = (TextView) findViewById(R.id.appearance_entry);
        tasteText = (TextView) findViewById(R.id.taste_entry);
        rating = (RatingBar) findViewById(R.id.rate_beer);

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
        String imageUri = arguments.getString(LagerOpenHelper.COLUMN_IMG);
        String lagerName = arguments.getString(LagerOpenHelper.COLUMN_NAME);
        String lagerAroma = arguments.getString(LagerOpenHelper.COLUMN_AROMA);
        String lagerAppearance = arguments.getString(LagerOpenHelper.COLUMN_APPEARANCE);
        String lagerTaste = arguments.getString(LagerOpenHelper.COLUMN_TASTE);
        int lagerRating = Integer.parseInt(arguments.getString(LagerOpenHelper.COLUMN_RATING));

        if (!imageUri.isEmpty()) {
            beerImage.setImageURI(Uri.parse(imageUri));
        }
        nameText.setText(lagerName);
        aromaText.setText(lagerAroma);
        appearanceText.setText(lagerAppearance);
        tasteText.setText(lagerTaste);
        rating.setRating(lagerRating);

        setTitle(lagerName);
    }
}