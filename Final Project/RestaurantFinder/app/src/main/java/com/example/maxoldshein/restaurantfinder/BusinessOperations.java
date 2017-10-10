package com.example.maxoldshein.restaurantfinder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maxoldshein on 5/9/17.
 */

public class BusinessOperations {
    private DatabaseWrapper databaseWrapper;
    private String[] BUSINESS_TABLE_COLUMNS =
            {DatabaseWrapper.BUSINESS_ID, DatabaseWrapper.BUSINESS_NAME, DatabaseWrapper.BUSINESS_PHONENUMBER,
                DatabaseWrapper.BUSINESS_ADDRESS, DatabaseWrapper.BUSINESS_ZIPCODE, DatabaseWrapper.BUSINESS_TYPE,
                DatabaseWrapper.BUSINESS_STYLE, DatabaseWrapper.BUSINESS_DELIVERY, DatabaseWrapper.BUSINESS_SUNDAYHOURS,
                DatabaseWrapper.BUSINESS_MONDAYHOURS, DatabaseWrapper.BUSINESS_TUESDAYHOURS, DatabaseWrapper.BUSINESS_WEDNESDAYHOURS,
                DatabaseWrapper.BUSINESS_THURSDAYHOURS, DatabaseWrapper.BUSINESS_FRIDAYHOURS, DatabaseWrapper.BUSINESS_SATURDAYHOURS,
                DatabaseWrapper.BUSINESS_DESCRIPTION, DatabaseWrapper.BUSINESS_LATITUDE, DatabaseWrapper.BUSINESS_LONGITUDE
            };
    private SQLiteDatabase database;

    public BusinessOperations(Context context) {
        databaseWrapper = new DatabaseWrapper(context);
    }

    public void open() {
        database = databaseWrapper.getWritableDatabase();
    }

    public void close() {
        databaseWrapper.close();
    }

    public Business addBusiness(String name, String phoneNumber, String address, String zipcode,
                                String type, String style, String delivery, String sundayHours,
                                String mondayHours, String tuesdayHours, String wednesdayHours, String thursdayhours,
                                String fridayHours, String saturdayHours, String description,
                                double latitude, double longitude) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(DatabaseWrapper.BUSINESS_NAME, name);
        contentValues.put(DatabaseWrapper.BUSINESS_PHONENUMBER, phoneNumber);
        contentValues.put(DatabaseWrapper.BUSINESS_ADDRESS, address);
        contentValues.put(DatabaseWrapper.BUSINESS_ZIPCODE, zipcode);
        contentValues.put(DatabaseWrapper.BUSINESS_TYPE, type);
        contentValues.put(DatabaseWrapper.BUSINESS_STYLE, style);
        contentValues.put(DatabaseWrapper.BUSINESS_DELIVERY, delivery);
        contentValues.put(DatabaseWrapper.BUSINESS_SUNDAYHOURS, sundayHours);
        contentValues.put(DatabaseWrapper.BUSINESS_MONDAYHOURS, mondayHours);
        contentValues.put(DatabaseWrapper.BUSINESS_TUESDAYHOURS, tuesdayHours);
        contentValues.put(DatabaseWrapper.BUSINESS_WEDNESDAYHOURS, wednesdayHours);
        contentValues.put(DatabaseWrapper.BUSINESS_THURSDAYHOURS, thursdayhours);
        contentValues.put(DatabaseWrapper.BUSINESS_FRIDAYHOURS, fridayHours);
        contentValues.put(DatabaseWrapper.BUSINESS_SATURDAYHOURS, saturdayHours);
        contentValues.put(DatabaseWrapper.BUSINESS_DESCRIPTION, description);
        contentValues.put(DatabaseWrapper.BUSINESS_LATITUDE, latitude);
        contentValues.put(DatabaseWrapper.BUSINESS_LONGITUDE, longitude);

        long businessId = database.insert(DatabaseWrapper.BUSINESSES, null, contentValues);

        Cursor cursor = database.query(DatabaseWrapper.BUSINESSES, BUSINESS_TABLE_COLUMNS,
                DatabaseWrapper.BUSINESS_ID + " = " + businessId, null, null, null, null);

        cursor.moveToFirst();
        Business newComment = parseBusiness(cursor);
        cursor.close();
        return newComment;
    }

    public List getAllBusinesses() {
        List businesses = new ArrayList();

        Cursor cursor = database.query(DatabaseWrapper.BUSINESSES, BUSINESS_TABLE_COLUMNS, null, null, null, null, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
            Business business = parseBusiness(cursor);
            businesses.add(business);
            cursor.moveToNext();
        }

        cursor.close();
        return businesses;
    }

    public List getAllBusinessesLikeName(String searchName) {
        List businesses = new ArrayList();

        Cursor cursor = database.query(DatabaseWrapper.BUSINESSES, BUSINESS_TABLE_COLUMNS, DatabaseWrapper.BUSINESS_NAME + " LIKE '%" + searchName + "%'", null, null, null, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
            Business business = parseBusiness(cursor);
            businesses.add(business);
            cursor.moveToNext();
        }

        cursor.close();
        return businesses;
    }

    public List getAllBusinessesLikeZipcode(String searchZipcode) {
        List businesses = new ArrayList();

        Cursor cursor = database.query(DatabaseWrapper.BUSINESSES, BUSINESS_TABLE_COLUMNS, DatabaseWrapper.BUSINESS_ZIPCODE + " LIKE '%" + searchZipcode + "%'", null, null, null, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
            Business business = parseBusiness(cursor);
            businesses.add(business);
            cursor.moveToNext();
        }

        cursor.close();
        return businesses;
    }

    public List getAllBusinessesLikeDelivery(String searchDelivery) {
        List businesses = new ArrayList();

        Cursor cursor = database.query(DatabaseWrapper.BUSINESSES, BUSINESS_TABLE_COLUMNS, DatabaseWrapper.BUSINESS_DELIVERY + " LIKE '%" + searchDelivery + "%'", null, null, null, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
            Business business = parseBusiness(cursor);
            businesses.add(business);
            cursor.moveToNext();
        }

        cursor.close();
        return businesses;
    }

    public Business parseBusiness(Cursor cursor) {
        Business business = new Business();

        business.setName(cursor.getString(1));
        business.setPhoneNumber(cursor.getString(2));
        business.setAddress(cursor.getString(3));
        business.setZipcode(cursor.getString(4));
        business.setType(cursor.getString(5));
        business.setStyle(cursor.getString(6));
        business.setDelivery(cursor.getString(7));
        business.setSundayHours(cursor.getString(8));
        business.setMondayHours(cursor.getString(9));
        business.setTuesdayHours(cursor.getString(10));
        business.setWednesdayHours(cursor.getString(11));
        business.setThursdayHours(cursor.getString(12));
        business.setFridayHours(cursor.getString(13));
        business.setSaturdayHours(cursor.getString(14));
        business.setDescription(cursor.getString(15));
        business.setLatitude(cursor.getDouble(16));
        business.setLongitude(cursor.getDouble(17));

        return business;
    }
}
