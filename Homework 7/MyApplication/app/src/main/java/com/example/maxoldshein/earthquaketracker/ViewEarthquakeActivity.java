package com.example.maxoldshein.earthquaketracker;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;

import java.util.ArrayList;
import java.util.List;

public class ViewEarthquakeActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapLoadedCallback, GoogleMap.OnMarkerClickListener {
    private GoogleMap googleMap;
    private LatLng myLocation = null;
    private LatLng latLng;

    double latitude = 0;
    double longitude = 0;
    int position;

    private ArrayList<Earthquake> earthquakes;
    private Earthquake currentEarthquake;
    private LatLng currentLatLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_earthquake);

        earthquakes = new ArrayList<>();

        Intent intent = getIntent();
        int size = intent.getIntExtra("size", 0);
        position =  intent.getIntExtra("position", 0);

        for(int i = 0; i < size; i++) {
            earthquakes.add((Earthquake) intent.getSerializableExtra("earthquakeObject " + i));
        }

        currentEarthquake = earthquakes.get(position);
        currentLatLng = new LatLng(currentEarthquake.getLatitude(), currentEarthquake.getLongitude());

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.veMap);
        mapFragment.getMapAsync(this);
    }

    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.setOnMapLoadedCallback(this);
        UiSettings mapSettings;
        mapSettings = googleMap.getUiSettings();
        mapSettings.setZoomControlsEnabled(true);
        googleMap.setMyLocationEnabled(false);
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 14));

    }

    public void onMapLoaded() {
        findEarthquake();

        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            public boolean onMarkerClick(Marker marker) {
                String string = marker.getSnippet();
                String earthquakeId = string.substring(15);
                Earthquake earthquake;

                for(int i = 0; i < earthquakes.size(); i++) {
                    if(earthquakeId.equals(earthquakes.get(i).getEarthquakeId())) {
                        earthquake = earthquakes.get(i);
                        Intent intent = new Intent(getApplicationContext(), ViewEarthquakeDetailsActivity.class);
                        intent.putExtra("earthquakeObject", earthquake);
                        startActivity(intent);
                        break;
                    }
                }

                return false;
            }
        });

        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener(){
            public void onInfoWindowClick(Marker marker) {
                String string = marker.getSnippet();
                String earthquakeId = string.substring(15);
                Earthquake earthquake;

                for(int i = 0; i < earthquakes.size(); i++) {
                    if(earthquakeId.equals(earthquakes.get(i).getEarthquakeId())) {
                        earthquake = earthquakes.get(i);
                        Intent intent = new Intent(getApplicationContext(), ViewEarthquakeDetailsActivity.class);
                        intent.putExtra("earthquakeObject", earthquake);
                        startActivity(intent);
                        break;
                    }
                }
            }
        });



        //myLocation = getMyLocation();

        /*if(myLocation == null) {
            Toast.makeText(this, "Unable to access your location. Consider enabling Location in your device's Settings.", Toast.LENGTH_LONG).show();
        } else {
            googleMap.addMarker(new MarkerOptions()
                                    .position(myLocation)
                                    .title("Me!")
            );
        }*/
    }

    /*public LatLng getMyLocation() {
        //Try to get the user's location three ways: GPS, Network, Passive
        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            Location location = null;

            if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            }

            if(location == null) {
                location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            }

            if(location == null) {
                location = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
            }

            if(location == null) {
                return null;
            } else {
                double myLatitude = location.getLatitude();
                double myLongitude = location.getLongitude();
                return new LatLng(myLatitude, myLongitude);
            }
        } else {
            return null;
        }
    }*/

    public void findEarthquake() {
        for(int i = 0; i < earthquakes.size(); i++) {
            latitude = earthquakes.get(i).getLatitude();
            longitude = earthquakes.get(i).getLongitude();

            if(googleMap == null) {
                googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.veMap)).getMap();
            }

            latLng = new LatLng(latitude, longitude);

            googleMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .title("Magnitude: " + earthquakes.get(i).getMagnitude())
                    .snippet("Earthquake Id: " + earthquakes.get(i).getEarthquakeId())
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
        }
    }

    public boolean onMarkerClick(Marker marker) {
        if(myLocation != null) {
            LatLng markerLatLng = marker.getPosition();
            googleMap.addPolyline(new PolylineOptions()
                                    .add(myLocation)
                                    .add(markerLatLng)
            );
            return true;
        } else {
            return false;
        }
    }
}
