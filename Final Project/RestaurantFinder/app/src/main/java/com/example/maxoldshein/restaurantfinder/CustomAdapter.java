package com.example.maxoldshein.restaurantfinder;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by maxoldshein on 5/9/17.
 */

public class CustomAdapter extends ArrayAdapter {
    private Activity context;
    private ArrayList<Business> businesses;

    public CustomAdapter(Activity context, ArrayList<Business> businesses) {
        super(context, R.layout.list_item, businesses);
        this.context = context;
        this.businesses = businesses;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View rowView = layoutInflater.inflate(R.layout.list_item, null, true);

        TextView name = (TextView) rowView.findViewById(R.id.lvName);
        TextView address = (TextView) rowView.findViewById(R.id.lvAddress);
        TextView type = (TextView) rowView.findViewById(R.id.lvType);
        TextView style = (TextView) rowView.findViewById(R.id.lvStyle);
        TextView delivery = (TextView) rowView.findViewById(R.id.lvDelivery);

        name.setText(businesses.get(position).getName());
        address.setText(businesses.get(position).getAddress() + " " + businesses.get(position).getZipcode());
        type.setText("Type: " + businesses.get(position).getType() + ", ");
        style.setText("Style: " + businesses.get(position).getStyle());
        delivery.setText("Delivery? " + businesses.get(position).getDelivery());

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ViewBusinessOnMapActivity.class);
                intent.putExtra("size", businesses.size());
                intent.putExtra("position", position);

                for(int i = 0; i < businesses.size(); i++) {
                    intent.putExtra("businessObject " + i, businesses.get(i));
                }

                context.startActivity(intent);
            }
        });

        return rowView;
    }
}
