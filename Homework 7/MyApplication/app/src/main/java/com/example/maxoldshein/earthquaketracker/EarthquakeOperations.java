package com.example.maxoldshein.earthquaketracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maxoldshein on 5/3/17.
 */

public class EarthquakeOperations {
    private DatabaseWrapper dbHelper;
    private String[] EARTHQUAKE_TABLE_COLUMNS =
            {DatabaseWrapper.EARTHQUAKE_ID, DatabaseWrapper.EARTHQUAKE_DATE, DatabaseWrapper.EARTHQUAKE_DEPTH,
                    DatabaseWrapper.EARTHQUAKE_EARTHQUAKEID, DatabaseWrapper.EARTHQUAKE_SOURCE, DatabaseWrapper.EARTHQUAKE_LATITUDE,
                    DatabaseWrapper.EARTHQUAKE_LONGITUDE, DatabaseWrapper.EARTHQUAKE_MAGNITUDE};
    private SQLiteDatabase database;

    public EarthquakeOperations(Context context) {
        dbHelper = new DatabaseWrapper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Earthquake addEarthquake(String date, double depth, String eId, String source, double latitude, double longitude, double magnitude) {
        //System.out.println(date + ", " + depth + ", " + eId + ", " + source + ", " + latitude + ", " + longitude + ", " + magnitude);
        ContentValues contentValues = new ContentValues();

        contentValues.put(DatabaseWrapper.EARTHQUAKE_DATE, date);
        contentValues.put(DatabaseWrapper.EARTHQUAKE_DEPTH, depth);
        contentValues.put(DatabaseWrapper.EARTHQUAKE_EARTHQUAKEID, eId);
        contentValues.put(DatabaseWrapper.EARTHQUAKE_SOURCE, source);
        contentValues.put(DatabaseWrapper.EARTHQUAKE_LATITUDE, latitude);
        contentValues.put(DatabaseWrapper.EARTHQUAKE_LONGITUDE, longitude);
        contentValues.put(DatabaseWrapper.EARTHQUAKE_MAGNITUDE, magnitude);

        long earthquakeId = database.insert(DatabaseWrapper.EARTHQUAKES, null, contentValues);

        Cursor cursor = database.query(DatabaseWrapper.EARTHQUAKES, EARTHQUAKE_TABLE_COLUMNS,
                DatabaseWrapper.EARTHQUAKE_ID + " = " + earthquakeId, null, null, null, null);

        cursor.moveToFirst();
        Earthquake newComment = parseEarthquake(cursor);
        cursor.close();
        return newComment;
    }

    public List getAllEarthquakes() {
        List earthquakes = new ArrayList();

        Cursor cursor = database.query(DatabaseWrapper.EARTHQUAKES, EARTHQUAKE_TABLE_COLUMNS, null, null, null, null, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
            Earthquake earthquake = parseEarthquake(cursor);
            earthquakes.add(earthquake);
            cursor.moveToNext();
        }

        cursor.close();
        return earthquakes;
    }

    public List getAllEarthquakesWithMagnitudeLargerThan(double magnitude) {
        List earthquakes = new ArrayList();

        Cursor cursor = database.query(DatabaseWrapper.EARTHQUAKES, EARTHQUAKE_TABLE_COLUMNS,
                DatabaseWrapper.EARTHQUAKE_MAGNITUDE + " > " + magnitude, null, null, null, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
            Earthquake earthquake = parseEarthquake(cursor);
            earthquakes.add(earthquake);
            cursor.moveToNext();
        }

        cursor.close();
        return earthquakes;
    }

    public List getAllEarthquakesInLatitudeRange(double latitudeMinimum, double latitudeMaximum) {
        List earthquakes = new ArrayList();

        Cursor cursor = database.query(DatabaseWrapper.EARTHQUAKES, EARTHQUAKE_TABLE_COLUMNS,
                DatabaseWrapper.EARTHQUAKE_LATITUDE + " <= " + latitudeMaximum + " AND " + DatabaseWrapper.EARTHQUAKE_LATITUDE + " >= " + latitudeMinimum,
                null, null, null, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
            Earthquake earthquake = parseEarthquake(cursor);
            earthquakes.add(earthquake);
            cursor.moveToNext();
        }

        cursor.close();
        return earthquakes;
    }

    public List getAllEarthquakesInLongitudeRange(double longitudeMinimum, double longitudeMaximum) {
        List earthquakes = new ArrayList();

        Cursor cursor = database.query(DatabaseWrapper.EARTHQUAKES, EARTHQUAKE_TABLE_COLUMNS,
                DatabaseWrapper.EARTHQUAKE_LONGITUDE + " <= " + longitudeMaximum + " AND " + DatabaseWrapper.EARTHQUAKE_LONGITUDE + " >= " + longitudeMinimum,
                null, null, null, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
            Earthquake earthquake = parseEarthquake(cursor);
            earthquakes.add(earthquake);
            cursor.moveToNext();
        }

        cursor.close();
        return earthquakes;
    }

    private Earthquake parseEarthquake(Cursor cursor) {
        Earthquake earthquake = new Earthquake();

        earthquake.setDate(cursor.getString(1));
        earthquake.setDepth(cursor.getDouble(2));
        earthquake.setEarthquakeId(cursor.getString(3));
        earthquake.setEarthquakeSource(cursor.getString(4));
        earthquake.setLatitude(cursor.getDouble(5));
        earthquake.setLongitude(cursor.getDouble(6));
        earthquake.setMagnitude(cursor.getDouble(7));

        //System.out.println("Earthquake: " + earthquake.toString());

        return earthquake;
    }
}
