package com.example.maxoldshein.restaurantfinder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by maxoldshein on 5/8/17.
 */

public class DatabaseWrapper extends SQLiteOpenHelper {
    public static final String BUSINESSES = "Businesses";
    public static final String BUSINESS_ID = "_id";
    public static final String BUSINESS_NAME = "_name";
    public static final String BUSINESS_PHONENUMBER = "_phoneNumber";
    public static final String BUSINESS_ADDRESS = "_address";
    public static final String BUSINESS_ZIPCODE = "_zipcode";
    public static final String BUSINESS_TYPE = "_type";
    public static final String BUSINESS_STYLE = "_style";
    public static final String BUSINESS_DELIVERY = "_delivery";
    public static final String BUSINESS_SUNDAYHOURS = "_sundayHours";
    public static final String BUSINESS_MONDAYHOURS = "_mondayHours";
    public static final String BUSINESS_TUESDAYHOURS = "_tuesdayHours";
    public static final String BUSINESS_WEDNESDAYHOURS = "_wednesdayHours";
    public static final String BUSINESS_THURSDAYHOURS = "_thursdayHours";
    public static final String BUSINESS_FRIDAYHOURS = "_fridayHours";
    public static final String BUSINESS_SATURDAYHOURS = "_saturdayHours";
    public static final String BUSINESS_DESCRIPTION = "_description";
    public static final String BUSINESS_LATITUDE = "_latitude";
    public static final String BUSINESS_LONGITUDE = "_longitude";

    private static final String DATABASE_NAME = "Businesses9.db";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE_1 =
            "create table " + BUSINESSES
                + "(" + BUSINESS_ID + " integer primary key autoincrement, "
                + BUSINESS_NAME + " text not null, "
                + BUSINESS_PHONENUMBER + " text not null, "
                + BUSINESS_ADDRESS + " text not null, "
                + BUSINESS_ZIPCODE + " text not null, "
                + BUSINESS_TYPE + " text not null, "
                + BUSINESS_STYLE + " text not null, "
                + BUSINESS_DELIVERY + " text not null, "
                + BUSINESS_SUNDAYHOURS + " text not null, "
                + BUSINESS_MONDAYHOURS + " text not null, "
                + BUSINESS_TUESDAYHOURS + " text not null, "
                + BUSINESS_WEDNESDAYHOURS + " text not null, "
                + BUSINESS_THURSDAYHOURS + " text not null, "
                + BUSINESS_FRIDAYHOURS + " text not null, "
                + BUSINESS_SATURDAYHOURS + " text not null, "
                + BUSINESS_DESCRIPTION + " text not null, "
                + BUSINESS_LATITUDE + " real, "
                + BUSINESS_LONGITUDE + " real);";

    public DatabaseWrapper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE_1);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + BUSINESSES);
        onCreate(db);
    }
}
