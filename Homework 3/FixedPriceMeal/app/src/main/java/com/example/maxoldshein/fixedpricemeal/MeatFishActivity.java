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

public class MeatFishActivity extends AppCompatActivity {

    private ListView listView;
    private String[] meatFish = {
            "Tatarbeefsteak",
            "Pfeffersteak",
            "Seezungenfilet",
            "Langosta",
    };
    private Integer[] imgId = {
            R.mipmap.tatar,
            R.mipmap.pfeffer,
            R.mipmap.seez,
            R.mipmap.langosta
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appetizer);

        CustomListAdapter adapter = new CustomListAdapter(this, meatFish, imgId);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = meatFish[+position];

                Intent localIntent = getIntent();
                Bundle bundle = localIntent.getExtras();
                bundle.putString("meatFish", selectedItem);
                localIntent.putExtras(bundle);
                setResult(Activity.RESULT_OK, localIntent);
                finish();
            }
        });
    }
}
