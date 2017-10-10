package com.example.maxoldshein.restaurantfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class DisplaySearchByZipcodeActivity extends AppCompatActivity {
    private TextView dsbzTextView;
    private ListView dsbzListView;

    private String searchZipcode;
    private CustomAdapter adapter;
    private BusinessOperations businessOperations;
    private ArrayList<Business> businesses;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_search_by_zipcode);

        dsbzTextView = (TextView) findViewById(R.id.dsbzTextView);
        dsbzListView = (ListView) findViewById(R.id.dsbzListView);

        Intent intent = getIntent();
        searchZipcode = intent.getStringExtra("searchZipcode");

        dsbzTextView.setText("Businesses with the zipcode " + searchZipcode + ":");

        businessOperations = new BusinessOperations(this);
        businessOperations.open();

        businesses = (ArrayList<Business>) businessOperations.getAllBusinessesLikeZipcode(searchZipcode);

        adapter = new CustomAdapter(this, businesses);
        dsbzListView.setAdapter(adapter);

        businessOperations.close();
    }
}
