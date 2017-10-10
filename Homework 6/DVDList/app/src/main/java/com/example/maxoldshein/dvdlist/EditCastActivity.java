package com.example.maxoldshein.dvdlist;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class EditCastActivity extends AppCompatActivity {
    private EditText actor;
    private EditText character;
    private ListView list;

    private ArrayList<Actor> actors;
    private CustomCastAdapter adapter;
    private ShowOperations showDbOperations;
    private String dbName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_cast);

        actor = (EditText) findViewById(R.id.ecActor);
        character = (EditText) findViewById(R.id.ecCharacter);
        list = (ListView) findViewById(R.id.ecList);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        dbName = bundle.getString("dbName");

        showDbOperations = new ShowOperations(this);
        showDbOperations.open();
        actors = (ArrayList) showDbOperations.getAllActors(dbName);
        showDbOperations.close();

        adapter = new CustomCastAdapter(this, actors);
        list.setAdapter(adapter);
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showDbOperations.open();
                showDbOperations.deleteActor(actors.get(position), dbName);
                showDbOperations.close();
                adapter.remove(actors.get(position));
                adapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    public void ecAdd(View button) {
        String actorName = actor.getText().toString();
        String characterName = character.getText().toString();

        if(actorName.equals("") || characterName.equals("")) {
            Toast.makeText(this, "Incomplete form!", Toast.LENGTH_LONG).show();
        } else {
            showDbOperations.open();
            showDbOperations.addActor(actorName, characterName, dbName);
            actors.add(new Actor(actorName, characterName));
            adapter.notifyDataSetChanged();
            showDbOperations.close();

            actor.setText("");
            character.setText("");
        }
    }

    public void ecSubmit(View button) {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        intent.putExtras(bundle);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}
