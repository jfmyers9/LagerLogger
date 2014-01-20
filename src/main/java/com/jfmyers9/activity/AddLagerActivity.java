package com.jfmyers9.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.jfmyers9.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import roboguice.activity.RoboActivity;

public class AddLagerActivity extends RoboActivity {

    private static final int CAMERA_REQUEST = 1877;
    private static final int MAX_HEIGHT = 650;

    private ImageView beerImage;
    private Uri imageUri;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle(R.string.add_beer_entry);
        setContentView(R.layout.add_lager_layout);

        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        beerImage = (ImageView) findViewById(R.id.beer_image);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.add_lager_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch(menuItem.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.add_picture_button:
                return addBeerPicture();
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
}