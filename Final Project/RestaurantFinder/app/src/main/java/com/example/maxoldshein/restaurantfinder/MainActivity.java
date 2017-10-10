package com.example.maxoldshein.restaurantfinder;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView businessesListView;

    JSONArray jsonArray;
    JSONObject jsonObject;
    ArrayList<Business> businesses;
    BusinessOperations businessOperations;
    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
    }

    public void initialize() {
        businessOperations = new BusinessOperations(this);
        businessOperations.open();

        businessesListView = (ListView) findViewById(R.id.businessesListView);

        businesses = (ArrayList<Business>) businessOperations.getAllBusinesses();

        if(businesses.isEmpty()) {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                new DownloadJSON().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            } else {
                new DownloadJSON().execute();
            }
        }

        adapter = new CustomAdapter(this, businesses);

        businessesListView.setAdapter(adapter);
    }

    protected void onResume() {
        businessOperations.open();
        super.onResume();
    }

    protected void onPause() {
        businessOperations.close();
        super.onPause();
    }

    private class DownloadJSON extends AsyncTask<Void, Void, Void>{
        protected Void doInBackground(Void... params) {
            jsonObject = JSONFunctions.getJSONFromURL("http://maxwelloldshein.com/JSON/restaurants.json");

            try {
                jsonArray = jsonObject.getJSONArray("businesses");

                for(int i = 0; i < jsonArray.length(); i++) {
                    jsonObject = jsonArray.getJSONObject(i);

                    String name = jsonObject.optString("name");
                    String phone = jsonObject.optString("phone");
                    String address = jsonObject.optString("address");
                    String zipcode = jsonObject.optString("zipcode");
                    String type = jsonObject.optString("type");
                    String style = jsonObject.optString("style");
                    String delivery = jsonObject.optString("delivery");
                    String sundayHours = jsonObject.optString("sundayHours");
                    String mondayHours = jsonObject.optString("mondayHours");
                    String tuesdayHours = jsonObject.optString("tuesdayHours");
                    String wednesdayHours = jsonObject.optString("wednesdayHours");
                    String thursdayHours = jsonObject.optString("thursdayHours");
                    String fridayHours = jsonObject.optString("fridayHours");
                    String saturdayHours = jsonObject.optString("saturdayHours");
                    String description = jsonObject.optString("description");
                    double latitude = jsonObject.optDouble("latitude");
                    double longitude = jsonObject.optDouble("longitude");

                    businessOperations.open();
                    Business business = businessOperations.addBusiness(name, phone, address, zipcode,
                                                                        type, style, delivery, sundayHours,
                                                                        mondayHours, tuesdayHours, wednesdayHours, thursdayHours,
                                                                        fridayHours, saturdayHours, description, latitude, longitude);

                    businesses.add(business);
                    businessOperations.close();
                }
            } catch(Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }

            return null;
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch(menuItem.getItemId()){
            case R.id.actionSearchName:
                Intent searchByNameIntent = new Intent(getApplicationContext(), SearchByNameActivity.class);
                startActivity(searchByNameIntent);
                return true;
            case R.id.actionSearchZipcode:
                Intent searchByZipcodeIntent = new Intent(getApplicationContext(), SearchByZipcodeActivity.class);
                startActivity(searchByZipcodeIntent);
                return true;
            case R.id.actionSearchDelivery:
                Intent searchByTypeIntent = new Intent(getApplicationContext(), SearchByDeliveryActivity.class);
                startActivity(searchByTypeIntent);
                return true;

        }

        return super.onOptionsItemSelected(menuItem);
    }
}
