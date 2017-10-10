package com.example.maxoldshein.earthquaketracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class LongitudeSearchActivity extends AppCompatActivity {
    private TextView lngEarthquakesTextView;
    private ListView lngListView;

    private CustomAdapter adapter;
    private EarthquakeOperations earthquakeOperations;
    private ArrayList<Earthquake> earthquakes;
    private double longitude;
    private double longitudeMinimum;
    private double longitudeMaximum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_longitude_search);

        lngEarthquakesTextView = (TextView) findViewById(R.id.lngEarthquakesTextView);
        lngListView = (ListView) findViewById(R.id.lngListView);

        Intent intent = getIntent();
        longitude = intent.getDoubleExtra("longitude", 0);
        longitudeMinimum = longitude - 10.0;
        longitudeMaximum = longitude + 10.0;

        if(longitudeMinimum < -180.0) {
            longitudeMinimum = -180.0;
        }

        if(longitudeMaximum > 180.0) {
            longitudeMaximum = 180.0;
        }

        lngEarthquakesTextView.setText("Earthquakes with longitudes in the range " + longitudeMinimum + " to " + longitudeMaximum + ":");

        earthquakeOperations = new EarthquakeOperations(this);
        earthquakeOperations.open();

        earthquakes = (ArrayList<Earthquake>) earthquakeOperations.getAllEarthquakesInLongitudeRange(longitudeMinimum, longitudeMaximum);

        adapter = new CustomAdapter(this, earthquakes);
        lngListView.setAdapter(adapter);
    }
}
