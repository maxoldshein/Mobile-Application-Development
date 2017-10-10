package com.example.maxoldshein.restaurantfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ViewBusinessDetailsActivity extends AppCompatActivity {
    private TextView vbdName;
    private TextView vbdAddress;
    private TextView vbdDescription;
    private TextView vbdPhoneNumber;
    private TextView vbdType;
    private TextView vbdStyle;
    private TextView vbdDelivery;
    private TextView vbdSundayHours;
    private TextView vbdMondayHours;
    private TextView vbdTuesdayHours;
    private TextView vbdWednedayHours;
    private TextView vbdThursdayHours;
    private TextView vbdFridayHours;
    private TextView vbdSaturdayHours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_business_details);

        vbdName = (TextView) findViewById(R.id.vbdName);
        vbdAddress = (TextView) findViewById(R.id.vbdAddress);
        vbdDescription = (TextView) findViewById(R.id.vbdDescription);
        vbdPhoneNumber = (TextView) findViewById(R.id.vbdPhoneNumer);
        vbdType = (TextView) findViewById(R.id.vbdType);
        vbdStyle = (TextView) findViewById(R.id.vbdStyle);
        vbdDelivery = (TextView) findViewById(R.id.vbdDelivery);
        vbdSundayHours = (TextView) findViewById(R.id.vbdSundayHours);
        vbdMondayHours = (TextView) findViewById(R.id.vbdMondayHours);
        vbdTuesdayHours = (TextView) findViewById(R.id.vbdTuesdayHours);
        vbdWednedayHours = (TextView) findViewById(R.id.vbdWednesdayHours);
        vbdThursdayHours = (TextView) findViewById(R.id.vbdThursdayHours);
        vbdFridayHours = (TextView) findViewById(R.id.vbdFridayHours);
        vbdSaturdayHours = (TextView) findViewById(R.id.vbdSaturdayHours);

        Intent intent = getIntent();
        Business business = (Business) intent.getSerializableExtra("businessObject");

        vbdName.setText(business.getName());
        vbdAddress.setText(business.getAddress() + " " + business.getZipcode());
        vbdDescription.setText(business.getDescription());
        vbdPhoneNumber.setText(business.getPhoneNumber());
        vbdType.setText("Type: " + business.getType());
        vbdStyle.setText("Style: " + business.getStyle());
        vbdDelivery.setText("Delivery? " + business.getDelivery());
        vbdSundayHours.setText("Sunday: " + business.getSundayHours());
        vbdMondayHours.setText("Monday: " + business.getMondayHours());
        vbdTuesdayHours.setText("Tuesday: " + business.getTuesdayHours());
        vbdWednedayHours.setText("Wednesday: " + business.getWednesdayHours());
        vbdThursdayHours.setText("Thursday: " + business.getThursdayHours());
        vbdFridayHours.setText("Friday: " + business.getFridayHours());
        vbdSaturdayHours.setText("Saturday: " + business.getSaturdayHours());
    }
}
