package com.example.maxoldshein.earthquaketracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ViewEarthquakeDetailsActivity extends AppCompatActivity {
    TextView earthquakeIdTextView;
    TextView dateTextView;
    TextView depthTextView;
    TextView sourceTextView;
    TextView latitudeTextView;
    TextView longitudeTextView;
    TextView magnitudeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_earthquake_details);

        earthquakeIdTextView = (TextView) findViewById(R.id.vedEarthquakeId);
        dateTextView = (TextView) findViewById(R.id.vedEarthquakeDate);
        depthTextView = (TextView) findViewById(R.id.vedEarthquakeDepth);
        sourceTextView = (TextView) findViewById(R.id.vedEarthquakeSource);
        latitudeTextView = (TextView) findViewById(R.id.vedEarthquakeLatitude);
        longitudeTextView = (TextView) findViewById(R.id.vedEarthquakeLongitude);
        magnitudeTextView = (TextView) findViewById(R.id.vedEarthquakeMagnitude);

        Intent intent = getIntent();
        Earthquake earthquake = (Earthquake) intent.getSerializableExtra("earthquakeObject");

        earthquakeIdTextView.setText("Earthquake Id: " + earthquake.getEarthquakeId());
        dateTextView.setText("Date: " + earthquake.getDate());
        depthTextView.setText("Depth: " + earthquake.getDepth());
        sourceTextView.setText("Source: " + earthquake.getEarthquakeSource());;
        latitudeTextView.setText("Latitude: " + earthquake.getLatitude());
        longitudeTextView.setText("Longitude: " + earthquake.getLongitude());
        magnitudeTextView.setText("Magnitude: " + earthquake.getMagnitude());
    }
}
