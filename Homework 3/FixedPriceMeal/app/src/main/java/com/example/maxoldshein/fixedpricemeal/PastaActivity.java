//Maxwell Oldshein
//Mobile Application Development
//Professor Signorile
//Homework 3 - Fixed Price Meal

package com.example.maxoldshein.fixedpricemeal;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class PastaActivity extends AppCompatActivity {

    private ListView listView;
    private String[] pastas = {
            "Spaghetti alla Vognole",
            "Penne Puttanesca",
            "Rigatoni alla Bolognese",
            "Cannelloni",
            "Arugala Ravioli",
            "Gnocchi alle Castagne"
    };
    private Integer[] imgId = {
            R.mipmap.ravioli,
            R.mipmap.penne,
            R.mipmap.ravioli,
            R.mipmap.ravioli,
            R.mipmap.ravioli,
            R.mipmap.ravioli
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appetizer);

        CustomListAdapter adapter = new CustomListAdapter(this, pastas, imgId);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = pastas[+position];

                Intent localIntent = getIntent();
                Bundle bundle = localIntent.getExtras();
                bundle.putString("pasta", selectedItem);
                localIntent.putExtras(bundle);
                setResult(Activity.RESULT_OK, localIntent);
                finish();
            }
        });
    }
}
