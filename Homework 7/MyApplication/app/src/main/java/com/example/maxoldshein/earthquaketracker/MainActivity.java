package com.example.maxoldshein.earthquaketracker;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText magnitudeEditText;
    private EditText longitudeEditText;
    private EditText latitudeEditText;
    private ListView earthquakeListView;

    JSONArray jsonArray;
    JSONObject jsonObject;
    ArrayList<Earthquake> earthquakes;
    EarthquakeOperations earthquakeOperations;
    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
    }

    public void initialize() {
        earthquakeOperations = new EarthquakeOperations(this);
        earthquakeOperations.open();
        //earthquakeOperations.drop();

        magnitudeEditText = (EditText) findViewById(R.id.magnitudeEditText);
        latitudeEditText = (EditText) findViewById(R.id.latitudeEditText);
        longitudeEditText = (EditText) findViewById(R.id.longitudeEditText);

        earthquakeListView = (ListView) findViewById(R.id.earthquakeListView);

        earthquakes = (ArrayList) earthquakeOperations.getAllEarthquakes();

        if(earthquakes.isEmpty()) {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                new DownloadJSON().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            } else {
                new DownloadJSON().execute();
            }
        }

        adapter = new CustomAdapter(this, earthquakes);

        earthquakeListView.setAdapter(adapter);
    }

    protected void onResume() {
        earthquakeOperations.open();
        initialize();
        super.onResume();
    }

    protected void onPause() {
        earthquakeOperations.close();
        super.onPause();
    }


    public void magnitudeSearch(View view) {
        String searchMagnitude = magnitudeEditText.getText().toString();

        if(searchMagnitude.equals("")) {
            Toast.makeText(getApplicationContext(), "Enter a magnitude before you search!", Toast.LENGTH_LONG).show();
        } else {
            double magnitude = Double.valueOf(searchMagnitude);

            Intent intent = new Intent(this, MagnitudeSearchActivity.class);
            intent.putExtra("magnitude", magnitude);

            startActivity(intent);
        }
    }

    public void latitudeSearch(View view) {
        String searchLatitude = latitudeEditText.getText().toString();

        if(searchLatitude.equals("")) {
           Toast.makeText(getApplicationContext(), "Enter a latitude before you search!", Toast.LENGTH_LONG).show();
        } else {
            double latitude = Double.valueOf(searchLatitude);

            Intent intent = new Intent(this, LatitudeSearchActivity.class);
            intent.putExtra("latitude", latitude);

            startActivity(intent);
        }
    }

    public void longitudeSearch(View view) {
        String searchLongitude = longitudeEditText.getText().toString();

        if(searchLongitude.equals("")) {
            Toast.makeText(getApplicationContext(), "Enter a longitude before you search!", Toast.LENGTH_LONG).show();
        } else {
            double longitude = Double.valueOf(searchLongitude);

            Intent intent = new Intent(this, LongitudeSearchActivity.class);
            intent.putExtra("longitude", longitude);

            startActivity(intent);
        }
    }

    private class DownloadJSON extends AsyncTask<Void, Void, Void>{
        protected Void doInBackground(Void... params) {
            jsonObject = JSONFunctions.getJSONFromURL("http://api.geonames.org/earthquakesJSON?north=90.0&south=-90.0&east=175.4&west=-180.0&lang=de&maxRows=100&username=bobandroid");

            try {
                jsonArray = jsonObject.getJSONArray("earthquakes");

                for(int i = 0; i < jsonArray.length(); i++) {
                    jsonObject = jsonArray.getJSONObject(i);

                    //Earthquake earthquake = new Earthquake();
                    //earthquake.setDate(jsonObject.optString("datetime"));
                    //earthquake.setDepth(jsonObject.optLong("depth"));
                    //earthquake.setEarthquakeId(jsonObject.optString("eqid"));
                    //earthquake.setEarthquakeSource(jsonObject.optString("src"));
                    //earthquake.setLatitude(jsonObject.optLong("lat"));
                    //earthquake.setLongitude(jsonObject.optLong("lng"));
                    //earthquake.setMagnitude(jsonObject.optLong("magnitude"));

                    String datetime = jsonObject.optString("datetime");
                    double depth = jsonObject.optDouble("depth");
                    double lng = jsonObject.optDouble("lng");
                    String src = jsonObject.optString("src");
                    String eqid = jsonObject.optString("eqid");
                    double magnitude = jsonObject.optDouble("magnitude");
                    double lat = jsonObject.getDouble("lat");

                    //System.out.println("count: " + i);
                    //System.out.println("datetime: " + datetime);
                    //System.out.println("depth: " + depth);
                    //System.out.println("lng: " + lng);
                    //System.out.println("src: " + src);
                    //System.out.println("eqid: " + eqid);
                    //System.out.println("magnitude: " + magnitude);
                    //System.out.println("lat: " + lat);

                    earthquakeOperations.open();
                    Earthquake earthquake = earthquakeOperations.addEarthquake(datetime, depth, eqid, src, lat, lng, magnitude);

                    earthquakes.add(earthquake);
                }
            } catch(Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }

            return null;
        }
    }
}
