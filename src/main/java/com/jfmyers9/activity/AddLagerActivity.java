package com.jfmyers9.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.inject.Inject;
import com.jfmyers9.LagerDatabaseHelper;
import com.jfmyers9.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;

public class AddLagerActivity extends RoboActivity {

    private static final int CAMERA_REQUEST = 1877;
    private static final int MAX_HEIGHT = 650;

    @InjectView(R.id.beer_image) protected ImageView beerImage;
    @InjectView(R.id.title_entry) protected EditText nameText;
    @InjectView(R.id.aroma_entry) protected EditText aromaText;
    @InjectView(R.id.appearance_entry) protected EditText appearanceText;
    @InjectView(R.id.taste_entry) protected EditText tasteText;
    @InjectView(R.id.rate_beer) protected RatingBar rating;
    protected Uri imageUri;

    @Inject protected LagerDatabaseHelper dbHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle(R.string.add_beer_entry);
        setContentView(R.layout.add_lager_layout);

        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.add_lager_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                exitWithoutSave();
                return true;
            case R.id.add_picture_button:
                return addBeerPicture();
            case R.id.cancel_button:
                exitWithoutSave();
                return true;
            case R.id.save_button:
                saveEntry();
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Uri photo;
            if (data != null) {
                photo = data.getData();
            } else {
                photo = imageUri;
            }
            beerImage.getLayoutParams().height = MAX_HEIGHT;
            beerImage.setImageURI(photo);
        }
    }

    public void saveEntry() {
        String name = nameText.getText().toString();
        String aroma = aromaText.getText().toString();
        String appearance = appearanceText.getText().toString();
        String taste = tasteText.getText().toString();
        String ratingNum = "" + (int) rating.getRating();
        String image = (imageUri != null) ? imageUri.toString() : "";

        dbHelper.addLagerEntry(name, aroma, appearance, taste, ratingNum, image);
    }

    /* Private Methods */

    private boolean addBeerPicture() {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File mediaStoreDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "LagerLogger");
            if (!mediaStoreDir.exists()) {
                if (!mediaStoreDir.mkdirs()) {
                    CharSequence text = "Directory does not exist.";
                    Toast.makeText(context, text, duration).show();
                    return false;
                }
            }
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            File mediaFile = new File(mediaStoreDir.getPath() + File.separator + "IMG" + timeStamp + ".jpg");

            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            imageUri = Uri.fromFile(mediaFile);
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            startActivityForResult(cameraIntent, CAMERA_REQUEST);
            return true;
        }
        CharSequence text = "SD Card not Mounted.";
        Toast.makeText(context, text, duration).show();
        return false;
    }

    private void exitWithoutSave() {
        if (imageUri != null) {
            File imageFile = new File(imageUri.getPath());
            imageFile.delete();
        }
        finish();
    }
}