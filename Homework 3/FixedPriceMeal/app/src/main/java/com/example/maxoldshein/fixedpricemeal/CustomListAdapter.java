//Maxwell Oldshein
//Mobile Application Development
//Professor Signorile
//Homework 3 - Fixed Price Meal

package com.example.maxoldshein.fixedpricemeal;

/**
 * Created by maxoldshein on 2/15/17.
 */

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomListAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] itemname;
    private final Integer[] imgid;

    public CustomListAdapter(Activity context, String[] itemname, Integer[] imgid) {
        super(context, R.layout.listitem, itemname);

        this.context=context;
        this.itemname=itemname;
        this.imgid=imgid;
    }
    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();

        View rowView=inflater.inflate(R.layout.listitem, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        txtTitle.setText(itemname[position]);
        imageView.setImageResource(imgid[position]);

        return rowView;
    };
}