package com.example.maxoldshein.earthquaketracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class LatitudeSearchActivity extends AppCompatActivity {
    private TextView latEarthquakesTextView;
    private ListView latListView;

    private CustomAdapter adapter;
    private EarthquakeOperations earthquakeOperations;
    private ArrayList<Earthquake> earthquakes;
    private double latitude;
    private double latitudeMinimum;
    private double latitudeMaximum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latitude_search);

        latEarthquakesTextView = (TextView) findViewById(R.id.latEarthquakesTextView);
        latListView = (ListView) findViewById(R.id.latListView);

        Intent intent = getIntent();
        latitude = intent.getDoubleExtra("latitude", 0);
        latitudeMinimum = latitude - 10.0;
        latitudeMaximum = latitude + 10.0;

        if(latitudeMinimum < -90.0) {
            latitudeMinimum = -90.0;
        }

        if(latitudeMaximum > 90.0) {
            latitudeMaximum = 90;
        }

        latEarthquakesTextView.setText("Earthsquakes with latitudes in the range " + latitudeMinimum + " to " + latitudeMaximum + ":");

        earthquakeOperations = new EarthquakeOperations(this);
        earthquakeOperations.open();

        earthquakes = (ArrayList<Earthquake>) earthquakeOperations.getAllEarthquakesInLatitudeRange(latitudeMinimum, latitudeMaximum);

        adapter = new CustomAdapter(this, earthquakes);
        latListView.setAdapter(adapter);

    }
}
