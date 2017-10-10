package com.example.maxoldshein.dvdlist;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by maxoldshein on 5/2/17.
 */

public class CustomCastAdapter extends ArrayAdapter<Actor> {
    private final Activity context;
    private final ArrayList<Actor> actors;

    public CustomCastAdapter(Activity context, ArrayList<Actor> actors) {
        super(context, R.layout.listview_normal, actors);
        this.context = context;
        this.actors = actors;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.listview_normal, null, true);
        TextView name = (TextView) rowView.findViewById(R.id.lvnName);
        TextView network = (TextView) rowView.findViewById(R.id.lvnNetwork);
        name.setText(actors.get(position).getName());
        network.setText(actors.get(position).getCharacter());
        return rowView;
    }

    public void remove(Actor actor) {
        actors.remove(actor);
        notifyDataSetChanged();
    }
}
