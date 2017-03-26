//Maxwell Oldshein
//Mobile Application Development
//Professor Signorile
//February 24, 2017

package com.example.maxoldshein.to_dolist;

import android.content.Context;
import android.speech.tts.TextToSpeech;
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
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText enterField;
    private List<String> toDoList;
    private ArrayAdapter<String> toDoListAdapter;
    final String filename = "toDoListFile.txt";
    TextToSpeech textToSpeech;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enterField = (EditText) findViewById(R.id.EnterField);

        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            public void onInit(int status) {
                textToSpeech.setPitch(1.1f);
                textToSpeech.setSpeechRate(1f);
                textToSpeech.setLanguage(Locale.US);
            }
        });
        toDoList = new ArrayList<String>();
        toDoListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, toDoList);

        final ListView listView = (ListView) findViewById(R.id.toDoList);
        listView.setAdapter(toDoListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long id) {
                String item =  (String) listView.getItemAtPosition(pos);
                textToSpeech.speak(item, TextToSpeech.QUEUE_FLUSH, null, null);
            }
        }
        );

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos, long id) {
                toDoList.remove(pos);
                toDoListAdapter.notifyDataSetChanged();
                saveListToFile(filename, toDoList);
                return true;
            }
        });

        if (readFromFile(filename) != null) {
            List<String> temporaryList = readFromFile(filename);

            for (String item : temporaryList) {
                toDoList.add(item);
            }

            toDoListAdapter.notifyDataSetChanged();
        }
    }

    public void onAddClick(View clickedButton) {
        String item = enterField.getText().toString();
        toDoList.add(item);
        enterField.setText("");
        toDoListAdapter.notifyDataSetChanged();
        saveListToFile(filename, toDoList);
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