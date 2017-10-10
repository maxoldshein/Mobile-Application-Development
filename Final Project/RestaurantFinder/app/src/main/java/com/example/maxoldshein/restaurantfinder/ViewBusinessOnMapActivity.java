package com.example.maxoldshein.restaurantfinder;

import android.content.Intent;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.location.Address;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ViewBusinessOnMapActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapLoadedCallback, GoogleMap.OnMarkerClickListener {
    private TextView vbomName;

    private GoogleMap googleMap;
    private LatLng myLocation = null;
    private LatLng latLng;

    double latitude = 0;
    double longitude = 0;
    int position;

    private ArrayList<Business> businesses;
    private List<Address> addresses;
    private ArrayList<String> locations;
    private Business currentBusiness;
    private LatLng currentLatLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_business_on_map);

        vbomName = (TextView) findViewById(R.id.vbomName);

        locations = new ArrayList<>();

        businesses = new ArrayList<>();

        Intent intent = getIntent();
        int size = intent.getIntExtra("size", 0);
        position =  intent.getIntExtra("position", 0);

        for(int i = 0; i < size; i++) {
            businesses.add((Business) intent.getSerializableExtra("businessObject " + i));
        }

        currentBusiness = businesses.get(position);
        System.out.println("VBOM Lat: " + currentBusiness.getLatitude() + ", Lng: " + currentBusiness.getLongitude());
        currentLatLng = new LatLng(currentBusiness.getLatitude(), currentBusiness.getLongitude());
        vbomName.setText(currentBusiness.getName());

        String address;
        for(int i = 0; i < size; i++) {
            address = businesses.get(i).getAddress();
            locations.add(address);
        }

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.vbomMap);
        mapFragment.getMapAsync(this);
    }

    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.setOnMapLoadedCallback(this);
        UiSettings mapSettings;
        mapSettings = googleMap.getUiSettings();
        mapSettings.setZoomControlsEnabled(true);
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 14));
    }

    public void onMapLoaded() {
        findBusiness();

        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            public void onInfoWindowClick(Marker marker) {
                String name = marker.getTitle();
                Business business;

                for(int i = 0; i < businesses.size(); i++) {
                    if(name.equals(businesses.get(i).getName())) {
                        business = businesses.get(i);
                        Intent intent = new Intent(getApplicationContext(), ViewBusinessDetailsActivity.class);
                        intent.putExtra("businessObject", business);
                        startActivity(intent);
                        break;
                    }
                }
            }
        });
    }

    public void findBusiness() {
        for(int i = 0; i < businesses.size(); i++) {
            latitude = businesses.get(i).getLatitude();
            longitude = businesses.get(i).getLongitude();

            if(googleMap == null) {
                googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.vbomMap)).getMap();
            }

            latLng = new LatLng(latitude, longitude);

            googleMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .title(businesses.get(i).getName())
                    .snippet(businesses.get(i).getAddress() + " " +businesses.get(i).getZipcode())
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
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
