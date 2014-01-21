package com.jfmyers9;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class LagerDatabaseHelper {
    private SQLiteDatabase database;
    private LagerOpenHelper dbHelper;

    public LagerDatabaseHelper(Context context) {
        dbHelper = new LagerOpenHelper(context);
    }

    public long addLagerEntry(String name, String aroma, String appearance, String taste,
                              String rating, String image) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(LagerOpenHelper.COLUMN_NAME, name);
        cv.put(LagerOpenHelper.COLUMN_AROMA, aroma);
        cv.put(LagerOpenHelper.COLUMN_APPEARANCE, appearance);
        cv.put(LagerOpenHelper.COLUMN_TASTE, taste);
        cv.put(LagerOpenHelper.COLUMN_RATING, rating);
        cv.put(LagerOpenHelper.COLUMN_IMG, image);

        return db.insert(LagerOpenHelper.DATABASE_NAME, null, cv);
    }
}
