package com.example.maxoldshein.advancedto_dolist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText enterField;
    private List<String> toDoLists;
    private ArrayAdapter<String> toDoListsAdapter;
    final String filename = "toDoLists";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enterField = (EditText) findViewById(R.id.EnterField);
        toDoLists = new ArrayList<String>();
        toDoListsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, toDoLists);

        final ListView listView = (ListView) findViewById(R.id.ToDoLists);
        listView.setAdapter(toDoListsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long id) {
                String toDoListFilename = (String) listView.getItemAtPosition(pos);
                Intent toDoListIntent = new Intent(MainActivity.this, ToDoListActivity.class);
                Bundle toDoListBundle = new Bundle();
                toDoListBundle.putString("filename", toDoListFilename);
                toDoListIntent.putExtras(toDoListBundle);
                startActivity(toDoListIntent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos, long id) {
                toDoLists.remove(pos);
                toDoListsAdapter.notifyDataSetChanged();
                saveListToFile(filename, toDoLists);
                return true;
            }
        });

        if (readFromFile(filename) != null) {
            List<String> temporaryList = readFromFile(filename);

            for (String item : temporaryList) {
                toDoLists.add(item);
            }

            toDoListsAdapter.notifyDataSetChanged();
        }
    }

    public void onCreateClick(View clickedButton) {
        String item = enterField.getText().toString();
        toDoLists.add(item);
        enterField.setText("");
        toDoListsAdapter.notifyDataSetChanged();
        saveListToFile(filename, toDoLists);
    }

    public void saveListToFile(String filename, List<?> listToBeSaved) {
        FileOutputStream fos;
        ObjectOutputStream oos;

        try {
            fos = openFileOutput(filename, Context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(listToBeSaved);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List readFromFile(String filename) {
        List retrievedList = new ArrayList();
        FileInputStream fis;
        ObjectInputStream ois;

        try {
            fis = openFileInput(filename);
            ois = new ObjectInputStream(fis);

            if (ois != null) {
                try {
                    retrievedList = (List<String>) ois.readObject();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                ois.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return retrievedList;
    }
}