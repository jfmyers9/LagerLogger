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

import com.jfmyers9.LagerDatabaseHelper;
import com.jfmyers9.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import roboguice.activity.RoboActivity;

public class AddLagerActivity extends RoboActivity {

    private static final int CAMERA_REQUEST = 1877;
    private static final int MAX_HEIGHT = 650;

    private ImageView beerImage;
    private EditText nameText, aromaText, appearanceText, tasteText;
    private RatingBar rating;
    private Uri imageUri;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle(R.string.add_beer_entry);
        setContentView(R.layout.add_lager_layout);

        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        beerImage = (ImageView) findViewById(R.id.beer_image);
        nameText = (EditText) findViewById(R.id.title_entry);
        aromaText = (EditText) findViewById(R.id.aroma_entry);
        appearanceText = (EditText) findViewById(R.id.appearance_entry);
        tasteText = (EditText) findViewById(R.id.taste_entry);
        rating = (RatingBar) findViewById(R.id.rate_beer);
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

    private void saveEntry() {
        String name = nameText.getText().toString();
        String aroma = aromaText.getText().toString();
        String appearance = appearanceText.getText().toString();
        String taste = tasteText.getText().toString();
        String ratingNum = "" + rating.getNumStars();

        LagerDatabaseHelper dbHelper = new LagerDatabaseHelper(getApplicationContext());
        dbHelper.addLagerEntry(name, aroma, appearance, taste, ratingNum, imageUri.toString());
    }
}