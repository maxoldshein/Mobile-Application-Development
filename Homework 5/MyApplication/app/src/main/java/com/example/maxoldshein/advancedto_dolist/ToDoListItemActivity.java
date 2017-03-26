package com.example.maxoldshein.advancedto_dolist;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ToDoListItemActivity extends AppCompatActivity {

    TextView toDoListItemHeader;
    EditText toDoListItemShortDescription;
    EditText toDoListItemLongDescription;
    String itemPosition;
    Intent localIntent;
    Bundle localBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list_item);

        localIntent = getIntent();
        localBundle = localIntent.getExtras();
        itemPosition = localBundle.getString("Item Position");
        String localShortDescription = localBundle.getString("Short Description");
        String localLongDescription = localBundle.getString("Long Description");

        toDoListItemHeader = (TextView) findViewById(R.id.ToDoListItemHeader);
        toDoListItemHeader.setText(localShortDescription);
        toDoListItemShortDescription = (EditText) findViewById(R.id.ToDoListItemShortDescription);
        toDoListItemShortDescription.setText(localShortDescription);
        toDoListItemLongDescription = (EditText) findViewById(R.id.ToDoListItemLongDescription);
        toDoListItemLongDescription.setText(localLongDescription);
    }

    public void onSaveClick(View clickedButton) {
        String itemPositon = itemPosition;
        String shortDescription = toDoListItemShortDescription.getText().toString();
        String longDescription = toDoListItemLongDescription.getText().toString();

        localBundle.putString("Item Position", itemPositon);
        localBundle.putString("Short Description", shortDescription);
        localBundle.putString("Long Description", longDescription);
        localIntent.putExtras(localBundle);
        setResult(Activity.RESULT_OK, localIntent);
        finish();
    }
}
