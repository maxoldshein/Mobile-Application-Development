package com.example.maxoldshein.dvdlist;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class DeleteShowActivity extends AppCompatActivity {
    private ShowOperations showDbOperations;
    private ListView dsList;
    private CustomDeleteAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_show);

        showDbOperations = new ShowOperations(this);
        showDbOperations.open();

        dsList = (ListView) findViewById(R.id.dsList);

        ArrayList shows = (ArrayList) showDbOperations.getAllShows();

        adapter = new CustomDeleteAdapter(this, shows);
        dsList.setAdapter(adapter);
    }

    public void dsDeleteShow(View view) {
        Intent intent = getIntent();

        Show show = adapter.getSelected();
        String name = show.getName();
        String network = show.getNetwork();
        showDbOperations.deleteShow(show);
        showDbOperations.close();

        Bundle bundle = intent.getExtras();
        bundle.putString("name", name);
        bundle.putString("network", network);
        intent.putExtras(bundle);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}
