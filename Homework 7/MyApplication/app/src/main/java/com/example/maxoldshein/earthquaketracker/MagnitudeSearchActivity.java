package com.example.maxoldshein.earthquaketracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MagnitudeSearchActivity extends AppCompatActivity {
    private TextView msEarthquakesTextView;
    private ListView msListView;

    private CustomAdapter adapter;
    private EarthquakeOperations earthquakeOperations;
    private ArrayList<Earthquake> earthquakes;
    private double magnitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magnitude_search);

        msEarthquakesTextView = (TextView) findViewById(R.id.msEarthquakesTextView);
        msListView = (ListView) findViewById(R.id.msListView);

        Intent intent = getIntent();
        magnitude = intent.getDoubleExtra("magnitude", 0);

        msEarthquakesTextView.setText("Earthquakes with magnitude greater than " +  magnitude + ":");

        earthquakeOperations = new EarthquakeOperations(this);
        earthquakeOperations.open();

        earthquakes = (ArrayList<Earthquake>) earthquakeOperations.getAllEarthquakesWithMagnitudeLargerThan(magnitude);

        adapter = new CustomAdapter(this, earthquakes);
        msListView.setAdapter(adapter);
    }
}
