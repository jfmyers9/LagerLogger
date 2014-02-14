package com.jfmyers9.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.jfmyers9.LagerOpenHelper;
import com.jfmyers9.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.util.ActivityController;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

@RunWith(RobolectricTestRunner.class)
public class ViewLagerActivityTest {
    private ActivityController<ViewLagerActivity> controller;
    private ViewLagerActivity activity;
    private String beer_name = "Sierra Nevada";
    private String beer_aroma = "Smelly";
    private String beer_appearance = "Brown";
    private String beer_taste = "Delicious";
    private String beer_rating = "4";

    @Mock private MenuItem menuItem;
    private ImageView beerImage;
    private TextView nameText;
    private TextView aromaText;
    private TextView appearanceText;
    private TextView tasteText;
    private RatingBar rating;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        menuItem = mock(MenuItem.class);
        controller = Robolectric.buildActivity(ViewLagerActivity.class);
        Intent intent = new Intent(Robolectric.getShadowApplication().getApplicationContext(), ViewLagerActivity.class);
        intent.putExtras(setupBundle());

        activity = controller.withIntent(intent).create().get();
        beerImage = (ImageView) activity.findViewById(R.id.beer_image);
        nameText = (TextView) activity.findViewById(R.id.title_entry);
        aromaText = (TextView) activity.findViewById(R.id.aroma_entry);
        appearanceText = (TextView) activity.findViewById(R.id.appearance_entry);
        tasteText = (TextView) activity.findViewById(R.id.taste_entry);
        rating = (RatingBar) activity.findViewById(R.id.rate_beer);
    }

    @Test
    public void testThatTheTitleIsCorrect() throws Exception {
        assertTrue(activity.getTitle().equals(beer_name));
    }

    @Test
    public void testThatAllValuesArePrefilledCorrectly() throws Exception {
        assertTrue(appearanceText.getText().toString().equals(beer_appearance));
        assertTrue(tasteText.getText().toString().equals(beer_taste));
        assertTrue(aromaText.getText().toString().equals(beer_aroma));
        assertTrue(rating.getRating() == Integer.parseInt(beer_rating));
        assertTrue(nameText.getText().toString().equals(beer_name));
    }

    /* Private Methods */

    public Bundle setupBundle() {
        Bundle arguments = new Bundle();
        arguments.putString(LagerOpenHelper.COLUMN_NAME, beer_name);
        arguments.putString(LagerOpenHelper.COLUMN_RATING, beer_rating);
        arguments.putString(LagerOpenHelper.COLUMN_AROMA, beer_aroma);
        arguments.putString(LagerOpenHelper.COLUMN_APPEARANCE, beer_appearance);
        arguments.putString(LagerOpenHelper.COLUMN_TASTE, beer_taste);
        arguments.putString(LagerOpenHelper.COLUMN_IMG, "");
        arguments.putLong(LagerOpenHelper.COLUMN_ID, 1);
        arguments.putString(LagerOpenHelper.COLUMN_CREATED_AT, "sometime");
        return arguments;
    }
}
