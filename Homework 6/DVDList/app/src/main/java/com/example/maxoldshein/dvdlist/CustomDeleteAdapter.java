package com.example.maxoldshein.dvdlist;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by maxoldshein on 5/2/17.
 */

public class CustomDeleteAdapter extends ArrayAdapter {
    private final Activity context;
    private final ArrayList<Show> shows;

    public CustomDeleteAdapter(Activity context, ArrayList<Show> shows) {
        super(context, R.layout.delete_list, shows);
        this.context = context;
        this.shows = shows;
    }

    int selectedPosition = 0;

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.delete_list, null, true);
        TextView textView = (TextView) rowView.findViewById(R.id.deleteItem);
        if(textView != null) {
            textView.setText(shows.get(position).getName());
        }

        RadioButton radioButton = (RadioButton) rowView.findViewById(R.id.dlIcon);
        radioButton.setChecked(position == selectedPosition);
        radioButton.setTag(position);
        radioButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                selectedPosition = (Integer) view.getTag();
                notifyDataSetChanged();
            }
        });

        return rowView;
    }

    public Show getSelected() {
        return shows.get(selectedPosition);
    }

    public void remove(Show show) {
        shows.remove(show);
    }
}
