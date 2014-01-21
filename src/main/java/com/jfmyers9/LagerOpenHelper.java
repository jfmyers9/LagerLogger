package com.jfmyers9;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LagerOpenHelper extends SQLiteOpenHelper {
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_RATING = "rating";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_AROMA = "aroma";
    public static final String COLUMN_APPEARANCE = "appearance";
    public static final String COLUMN_TASTE = "taste";
    public static final String COLUMN_IMG = "image";
    public static final String COLUMN_CREATED_AT = "created_at";

    public static final String DATABASE_NAME = "lager";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_TABLE_CREATE = "CREATE TABLE " +
            DATABASE_NAME + " (" + COLUMN_ID + " integer primary key autoincrement, " +
            COLUMN_NAME + " text not null, " + COLUMN_RATING + " text not null, " +
            COLUMN_APPEARANCE + " text, " + COLUMN_AROMA + " text, " +
            COLUMN_TASTE + " text, " + COLUMN_IMG + " text, " +
            COLUMN_CREATED_AT + " DEFAULT CURRENT_TIMESTAMP);";

    public LagerOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);
        onCreate(db);
    }
}
