package com.example.maxoldshein.dvdlist;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ShowOperations showDBOperation;
    private CustomAdapter adapter;
    private ArrayList<Show> values;

    private ListView showList;
    private TextView empty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
    }

    public void initialize() {
        showDBOperation = new ShowOperations(this);
        showDBOperation.open();

        empty = (TextView) findViewById(android.R.id.empty);

        showList = (ListView) findViewById(R.id.list);
        showList.setEmptyView(empty);

        values = (ArrayList) showDBOperation.getAllShows();

        adapter = new CustomAdapter(this, values);

        showList.setAdapter(adapter);
    }

    protected void onResume() {
        showDBOperation.open();
        initialize();
        super.onResume();
    }

    protected void onPause() {
        showDBOperation.close();
        super.onPause();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();

        if(id == R.id.actionAdd) {
            Intent intent = new Intent(MainActivity.this, AddShowActivity.class);
            Bundle bundle = new Bundle();
            intent.putExtras(bundle);
            startActivityForResult(intent, 101);
            return true;
        }

        if(id == R.id.actionDelete) {
            Intent intent = new Intent(MainActivity.this, DeleteShowActivity.class);
            Bundle bundle = new Bundle();
            intent.putExtras(bundle);
            startActivityForResult(intent, 102);
            return true;
        }

        return super.onOptionsItemSelected(menuItem);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            if((requestCode == 101) && (resultCode == Activity.RESULT_OK)) {
                initialize();
            }

            if((requestCode == 102) && (resultCode == Activity.RESULT_OK)) {
                initialize();
            }
        } catch (Exception e) {
            System.out.println("Problems -- " + requestCode + " " + resultCode);
        }
    }
}
