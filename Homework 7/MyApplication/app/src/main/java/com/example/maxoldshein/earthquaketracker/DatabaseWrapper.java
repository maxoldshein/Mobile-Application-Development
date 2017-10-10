package com.example.maxoldshein.earthquaketracker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by maxoldshein on 5/3/17.
 */

public class DatabaseWrapper extends SQLiteOpenHelper {
    public static final String EARTHQUAKES = "Earthquakes";
    public static final String EARTHQUAKE_ID ="_id";
    public static final String EARTHQUAKE_DATE = "_date";
    public static final String EARTHQUAKE_DEPTH = "_depth";
    public static final String EARTHQUAKE_EARTHQUAKEID = "_earthquakeid";
    public static final String EARTHQUAKE_SOURCE = "_source";
    public static final String EARTHQUAKE_LATITUDE = "_latitude";
    public static final String EARTHQUAKE_LONGITUDE = "_longitude";
    public static final String EARTHQUAKE_MAGNITUDE = "_magnitude";

    private static final String DATABASE_NAME = "Earthquakes7.db";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE_1 =
            "create table " + EARTHQUAKES
                    + "(" + EARTHQUAKE_ID + " integer primary key autoincrement, "
                    + EARTHQUAKE_DATE + " text not null, "
                    + EARTHQUAKE_DEPTH + " real, "
                    + EARTHQUAKE_EARTHQUAKEID + " text not null, "
                    + EARTHQUAKE_SOURCE + " text not null, "
                    + EARTHQUAKE_LATITUDE + " real, "
                    + EARTHQUAKE_LONGITUDE + " real, "
                    + EARTHQUAKE_MAGNITUDE + " real);";
    private static final String DATABASE_DROP_1 = "DELETE * FROM " + EARTHQUAKES;


    public DatabaseWrapper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE_1);
    }

    public void delete(SQLiteDatabase db) {
        db.execSQL(DATABASE_DROP_1);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + EARTHQUAKES);
        onCreate(db);
    }
}
