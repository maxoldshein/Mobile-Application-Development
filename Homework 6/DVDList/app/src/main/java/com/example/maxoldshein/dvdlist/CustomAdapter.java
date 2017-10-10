package com.example.maxoldshein.dvdlist;

import android.app.Activity;
import android.content.Intent;
import android.media.Rating;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by maxoldshein on 5/1/17.
 */

public class CustomAdapter extends ArrayAdapter<Show> {
    private final Activity context;
    private ArrayList<Show> shows;

    public CustomAdapter(Activity context, ArrayList<Show> shows) {
        super(context, R.layout.listview_item, shows);
        this.context = context;
        this.shows = shows;
    }

    public void update(ArrayList<Show> newShows) {
        this.shows = newShows;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.listview_item, null, true);
        TextView name = (TextView) rowView.findViewById(R.id.name);
        TextView network = (TextView) rowView.findViewById(R.id.network);
        RatingBar rating = (RatingBar) rowView.findViewById(R.id.rating_bar);
        name.setText(shows.get(position).getName());
        network.setText(shows.get(position).getNetwork());

        if(shows.get(position).getRating() != null) {
            rating.setRating(Float.parseFloat(shows.get(position).getRating()));
            System.out.println("RATING: " + shows.get(position).getRating());
        }

        rowView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Show currentShow = (Show) view.getTag(R.id.show);
                Intent intent = new Intent(context, ViewShowActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("name", currentShow.getName());
                bundle.putString("network", currentShow.getNetwork());
                bundle.putInt("episodes", currentShow.getEpisodes());
                bundle.putLong("id", currentShow.getId());
                bundle.putString("dbName", currentShow.getDbName());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

        rowView.setTag(R.id.show, shows.get(position));

        return rowView;
    }
}
