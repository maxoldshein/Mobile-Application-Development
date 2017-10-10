package com.example.maxoldshein.dvdlist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by maxoldshein on 5/1/17.
 */

public class DatabaseWrapper extends SQLiteOpenHelper {
    public static final String SHOWS = "Shows";
    public static final String SHOW_ID = "_id";
    public static final String SHOW_NAME = "_name";
    public static final String SHOW_NETWORK = "_network";
    public static final String SHOW_RATING = "_rating";
    public static final String SHOW_EPISODES = "_episodes";
    public static final String CAST_DB = "_dbName";

    public static final String ACTOR_ID = "_id";
    public static final String ACTOR_NAME = "_name";
    public static final String ACTOR_CHARACTER = "_character";

    public static final String DATABASE_NAME = "TVShows5.db";
    public static final int DATABASE_VERSION = 1;

    private ArrayList<String> tableNames = new ArrayList<>();

    private static final String DATABASE_CREATE_1 = "create table " + SHOWS
            + "(" + SHOW_ID + " integer primary key autoincrement, "
            + SHOW_NAME + " text not null, "
            + SHOW_NETWORK + " text not null, "
            + SHOW_RATING+ " text not null, "
            + SHOW_EPISODES + " integer, "
            + CAST_DB + " text not null);";

    public DatabaseWrapper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE_1);

        for(String table : tableNames) {
            createTable(db, table);
        }
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + SHOWS);

        for(String table : tableNames) {
            db.execSQL("DROP TABLE IF EXISTS " + table);
        }

        onCreate(db);
    }

    public void createTable(SQLiteDatabase db, String tableName) {
        db.execSQL("create table " + tableName
                    + "(" + ACTOR_ID + " integer primary key autoincrement, "
                    + ACTOR_NAME + " text not null, "
                    + ACTOR_CHARACTER + " text not null);");
        System.out.println(tableName  + " was created.");
        tableNames.add(tableName);
    }
}
