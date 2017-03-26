//Maxwell Oldshein
//Mobile Application Development
//Professor Signorile
//Homework 2i

package com.example.maxoldshein.gamesbymaxwelloldshein;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {

    private ListView lv;
    private ArrayAdapter itemsAdapter;
    private String[] applicationList = new String[]{"Hangman", "Tic Tac Toe"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, applicationList);

        ListView listView = (ListView) findViewById(R.id.gameList);
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int index, long id) {
                handleClick(index);
            }
        });
    }

    private void handleClick(int index) {
        String text = applicationList[index];

        if (text.equals("Hangman")) {
            Intent intent = new Intent(this, HangmanGame.class);
            startActivity(intent);
        } else if (text.equals("Tic Tac Toe")) {
            Intent intent = new Intent(this, TicTacToeGame.class);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Game not available!", Toast.LENGTH_SHORT).show();
        }
    }
}
