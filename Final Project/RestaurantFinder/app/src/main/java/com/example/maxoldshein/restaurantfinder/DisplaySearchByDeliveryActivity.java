package com.example.maxoldshein.restaurantfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class DisplaySearchByDeliveryActivity extends AppCompatActivity {
    private TextView dsbdTextView;
    private ListView dsbdListView;


    private String searchDelivery;
    private CustomAdapter adapter;
    private BusinessOperations businessOperations;
    private ArrayList<Business> businesses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_search_by_delivery);

        dsbdTextView = (TextView) findViewById(R.id.dsbdTextView);
        dsbdListView = (ListView) findViewById(R.id.dsbdListView);

        Intent intent = getIntent();
        searchDelivery = intent.getStringExtra("searchDelivery");

        if(searchDelivery.equals("Yes")) {
            dsbdTextView.setText("Businesses that deliver:");
        } else {
            dsbdTextView.setText("Businesses that only offer pick-up:");
        }

        businessOperations = new BusinessOperations(this);
        businessOperations.open();

        businesses = (ArrayList<Business>) businessOperations.getAllBusinessesLikeDelivery(searchDelivery);

        adapter = new CustomAdapter(this, businesses);
        dsbdListView.setAdapter(adapter);

        businessOperations.close();
    }
}
