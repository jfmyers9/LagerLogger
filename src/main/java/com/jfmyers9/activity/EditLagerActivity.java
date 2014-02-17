package com.jfmyers9.activity;

import android.net.Uri;
import android.os.Bundle;

import com.jfmyers9.LagerEntry;
import com.jfmyers9.R;
import com.jfmyers9.fragment.LagerHistoryFragment;

public class EditLagerActivity extends AddLagerActivity {
    private LagerEntry currentEntry;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle(R.string.edit_beer_entry);
        currentEntry = getIntent().getExtras().getParcelable(LagerHistoryFragment.LAGER_KEY);
        prefillLagerInformation();
    }

    /* Private Methods */

    private void prefillLagerInformation() {
        int lagerRating = Integer.parseInt(currentEntry.getRating());

        if (!currentEntry.getImage().isEmpty()) {
            imageUri = Uri.parse(currentEntry.getImage());
            beerImage.setImageURI(imageUri);
        }
        nameText.setText(currentEntry.getName());
        aromaText.setText(currentEntry.getAroma());
        appearanceText.setText(currentEntry.getAppearance());
        tasteText.setText(currentEntry.getTaste());
        rating.setRating(lagerRating);

        setTitle(currentEntry.getName());
    }

    @Override
    public void saveEntry() {
        currentEntry.setName(nameText.getText().toString());
        currentEntry.setAroma(aromaText.getText().toString());
        currentEntry.setAppearance(appearanceText.getText().toString());
        currentEntry.setTaste(tasteText.getText().toString());
        currentEntry.setRating("" + (int) rating.getRating());
        String image = (imageUri != null) ? imageUri.toString() : "";
        currentEntry.setImage(image);
        dbHelper.editLagerEntry(currentEntry.getName(), currentEntry.getAroma(),
                currentEntry.getAppearance(), currentEntry.getTaste(),
                currentEntry.getRating(), currentEntry.getImage(), currentEntry.getId());
    }
}
