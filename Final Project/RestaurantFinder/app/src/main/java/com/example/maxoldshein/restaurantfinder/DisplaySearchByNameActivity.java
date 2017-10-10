package com.example.maxoldshein.restaurantfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class DisplaySearchByNameActivity extends AppCompatActivity {
    private TextView dsbnTextView;
    private ListView dsbnListView;

    private String searchName;
    private CustomAdapter adapter;
    private BusinessOperations businessOperations;
    private ArrayList<Business> businesses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_search_by_name);

        dsbnTextView = (TextView) findViewById(R.id.dsbnTextView);
        dsbnListView = (ListView)  findViewById(R.id.dsbnListView);

        Intent intent = getIntent();
        searchName = intent.getStringExtra("searchName");

        dsbnTextView.setText("Businesses with names like " + "\"" + searchName + "\":");

        businessOperations = new BusinessOperations(this);
        businessOperations.open();

        businesses = (ArrayList<Business>) businessOperations.getAllBusinessesLikeName(searchName);

        adapter = new CustomAdapter(this, businesses);
        dsbnListView.setAdapter(adapter);
    }
}
