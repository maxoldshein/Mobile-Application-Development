package com.example.maxoldshein.earthquaketracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by maxoldshein on 5/2/17.
 */

public class CustomAdapter extends ArrayAdapter {
    private Activity context;
    private ArrayList<Earthquake> earthquakes;

    public CustomAdapter(Activity context, ArrayList<Earthquake> earthquakes) {
        super(context, R.layout.list_item, earthquakes);
        this.context = context;
        this.earthquakes = earthquakes;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View rowView = layoutInflater.inflate(R.layout.list_item, null, true);

        TextView magnitude = (TextView) rowView.findViewById(R.id.ltMagnitude);
        TextView latitude = (TextView) rowView.findViewById(R.id.ltLatitude);
        TextView longitude = (TextView) rowView.findViewById(R.id.ltLongitude);

        magnitude.setText("Mag.: " + String.valueOf(earthquakes.get(position).getMagnitude()) + ", ");
        latitude.setText("Lat.: " + String.valueOf(earthquakes.get(position).getLatitude()) + ", ");
        longitude.setText("Lng.: "+ String.valueOf(earthquakes.get(position).getLongitude()));

        //System.out.println("Earthquake: " + earthquakes.get(position).toString());

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Earthquake currentEarthquake = (Earthquake) view.getTag(R.id.earthquake);

                Intent intent = new Intent(context, ViewEarthquakeActivity.class);
                intent.putExtra("size", earthquakes.size());
                intent.putExtra("position", position);

                for(int i = 0; i < earthquakes.size(); i++) {
                    intent.putExtra("earthquakeObject " + i, earthquakes.get(i));
                }

                context.startActivity(intent);
            }
        });

        rowView.setTag(R.id.earthquake, earthquakes.get(position));

        return rowView;
    }
}
