package com.jfmyers9;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.inject.Inject;

import java.util.ArrayList;

public class LagerDatabaseHelper {
    private static final String QUERY_ALL = "SELECT * FROM " + LagerOpenHelper.DATABASE_NAME + ";";
    private static final String QUERY_SINGLE = "SELECT * FROM " + LagerOpenHelper.DATABASE_NAME + " WHERE id = ";
    private static final String EDIT_CLAUSE = "id = ?";
    private LagerOpenHelper dbHelper;

    @Inject
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

    public long editLagerEntry(String name, String aroma, String appearance, String taste,
                               String rating, String image, Long id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(LagerOpenHelper.COLUMN_NAME, name);
        cv.put(LagerOpenHelper.COLUMN_AROMA, aroma);
        cv.put(LagerOpenHelper.COLUMN_APPEARANCE, appearance);
        cv.put(LagerOpenHelper.COLUMN_TASTE, taste);
        cv.put(LagerOpenHelper.COLUMN_RATING, rating);
        cv.put(LagerOpenHelper.COLUMN_IMG, image);

        String[] whereArgs = { Long.toString(id) };

        return db.update(LagerOpenHelper.DATABASE_NAME, cv, EDIT_CLAUSE, whereArgs);
    }
    
    public ArrayList<LagerEntry> getAllLagerEntries() {
        ArrayList<LagerEntry> result = new ArrayList<LagerEntry>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery(QUERY_ALL, null);
        c.moveToFirst();
        while(c.isAfterLast() == false) {
            result.add(buildLagerEntry(c));
            c.moveToNext();
        }
        return result;
    }

    public LagerEntry getLagerEntryById(Long id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery(QUERY_SINGLE + id + ";", null);
        c.moveToFirst();
        return buildLagerEntry(c);
    }

    /* Private Methods */

    private LagerEntry buildLagerEntry(Cursor c) {
        String name = c.getString(c.getColumnIndex(LagerOpenHelper.COLUMN_NAME));
        String aroma = c.getString(c.getColumnIndex(LagerOpenHelper.COLUMN_AROMA));
        String appearance = c.getString(c.getColumnIndex(LagerOpenHelper.COLUMN_APPEARANCE));
        String taste = c.getString(c.getColumnIndex(LagerOpenHelper.COLUMN_TASTE));
        String rating = c.getString(c.getColumnIndex(LagerOpenHelper.COLUMN_RATING));
        String img = c.getString(c.getColumnIndex(LagerOpenHelper.COLUMN_IMG));
        String created_at = c.getString(c.getColumnIndex(LagerOpenHelper.COLUMN_CREATED_AT));
        long id = c.getInt(c.getColumnIndex(LagerOpenHelper.COLUMN_ID));
        LagerEntry lagerEntry = new LagerEntry(name, rating, aroma, appearance, taste, img);
        lagerEntry.setId(id);
        lagerEntry.setCreatedAt(created_at);
        return lagerEntry;
    }
}
