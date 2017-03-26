package com.example.maxoldshein.advancedto_dolist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ToDoListActivity extends AppCompatActivity {

    String filename;
    EditText taskField;
    TextView toDoListHeader;
    private List<TaskObject> toDoList;
    private TaskObjectAdapter toDoListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);

        Intent localIntent = getIntent();
        Bundle localBundle = localIntent.getExtras();
        String tempFilename = localBundle.getString("filename");
        Uri uri = Uri.parse(tempFilename);
        filename = uri.getPath();
        System.out.print(filename);

        taskField = (EditText) findViewById(R.id.TaskField);
        toDoListHeader = (TextView) findViewById(R.id.ToDoListHeader);
        toDoListHeader.setText(filename);
        toDoList = new ArrayList<TaskObject>();
        toDoListAdapter = new TaskObjectAdapter(this, toDoList);

        final ListView listView = (ListView) findViewById(R.id.ToDoList);
        listView.setAdapter(toDoListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long id) {
                TaskObject taskObject = toDoList.get(pos);
                Intent toDoListItemIntent = new Intent(ToDoListActivity.this, ToDoListItemActivity.class);
                Bundle toDoListItemBundle = new Bundle();
                toDoListItemBundle.putString("Item Position", Integer.toString(pos));
                toDoListItemBundle.putString("Short Description", taskObject.getShortDescription());
                toDoListItemBundle.putString("Long Description", taskObject.getLongDescription());
                toDoListItemIntent.putExtras(toDoListItemBundle);
                startActivityForResult(toDoListItemIntent, 101);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos, long id) {
                toDoList.remove(pos);
                toDoListAdapter.notifyDataSetChanged();
                saveListToFile(filename, toDoList);
                return true;
            }
        });

        if (readFromFile(filename) != null) {
            List<TaskObject> temporaryList = readFromFile(filename);

            for (TaskObject item : temporaryList) {
                toDoList.add(item);
            }

            toDoListAdapter.notifyDataSetChanged();
        }
    }

    public void onAddClick(View clickButton) {
        String item = taskField.getText().toString();
        TaskObject taskObject = new TaskObject(item, "");
        toDoList.add(taskObject);
        taskField.setText("");
        toDoListAdapter.notifyDataSetChanged();
        saveListToFile(filename, toDoList);
    }

    public void onSaveAndReturnClick(View clickedButton) {
        saveListToFile(filename, toDoList);
        finish();
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
                    retrievedList = (List<TaskObject>) ois.readObject();
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

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            if ((requestCode == 101) && (resultCode == Activity.RESULT_OK)) {
                Bundle toDoListItemActivityResults = data.getExtras();
                String toDoListItemPosition = toDoListItemActivityResults.getString("Item Position");
                int itemPosition = Integer.parseInt(toDoListItemPosition);
                String toDoListItemShortDescription = toDoListItemActivityResults.getString("Short Description");
                String toDoListItemLongDescription = toDoListItemActivityResults.getString("Long Description");

                TaskObject taskObject = toDoList.get(itemPosition);
                taskObject.setShortDescription(toDoListItemShortDescription);
                taskObject.setLongDescripton(toDoListItemLongDescription);
                toDoList.set(itemPosition, taskObject);
                saveListToFile(filename, toDoList);
                toDoListAdapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
