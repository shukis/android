package com.example.pavel.swipe;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.R.attr.version;

/**
 * Created by Pavel on 14.02.2017.
 */

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "statsDB";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_STATISTICS = "statistics";

    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_POINTS = "points";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_STATISTICS + "(" + KEY_ID + " integer primary key,"
                + KEY_NAME + " TEXT,"
                + KEY_POINTS + " integer);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_STATISTICS);
        onCreate(db);
    }
}
