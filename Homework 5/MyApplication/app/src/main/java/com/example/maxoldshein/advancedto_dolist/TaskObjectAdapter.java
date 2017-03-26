package com.example.maxoldshein.advancedto_dolist;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maxoldshein on 3/25/17.
 */

public class TaskObjectAdapter extends ArrayAdapter<TaskObject> {
    ViewHolder viewHolder;
    List<TaskObject> items;
    Activity context;

    private static class ViewHolder {
        private CheckBox checkBox;
        private TextView itemView;
    }

    public TaskObjectAdapter(Activity context, List<TaskObject> items) {
        super(context, R.layout.list_item, items);

        this.context = context;
        this.items = items;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(this.getContext())
                    .inflate(R.layout.list_item, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.ListCheckBox);
            viewHolder.itemView = (TextView) convertView.findViewById(R.id.ListTextView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        if (items.get(position).getIsComplete()) {
            viewHolder.checkBox.setChecked(true);
        } else {
            viewHolder.checkBox.setChecked(false);
        }

        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CheckBox checkBox = (CheckBox) view;
                TaskObject currentTaskObject = (TaskObject) checkBox.getTag();
                currentTaskObject.setIsComplete(checkBox.isChecked());
            }
        });

        TaskObject item = getItem(position);
        if (item!= null) {
            viewHolder.itemView.setText(String.format("%s", item.shortDescription));
        }

        viewHolder.checkBox.setTag(items.get(position));

        return convertView;
    }
}
