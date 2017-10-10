package com.example.maxoldshein.dvdlist;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddShowActivity extends AppCompatActivity {
    private EditText asShowName;
    private EditText asShowNetwork;
    private ShowOperations showDbOperations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_show);

        asShowName = (EditText) findViewById(R.id.asShowName);
        asShowNetwork = (EditText) findViewById(R.id.asShowNetwork);
    }

    public void asAddShow(View button) {
        String name = asShowName.getText().toString();
        String network = asShowNetwork.getText().toString();

        if((name.equals("")) || (network.equals(""))) {
            Toast.makeText(this,"The form is incomplete!", Toast.LENGTH_LONG).show();
        } else {
            showDbOperations = new ShowOperations(this);
            showDbOperations.open();
            Show show = showDbOperations.addShow(name, network);
            String tableName = show.getDbName();
            showDbOperations.createTable(tableName);
            showDbOperations.close();

            String showName = show.getName();
            String showNetwork = show.getNetwork();

            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            bundle.putString("name", showName);
            bundle.putString("network", showNetwork);
            intent.putExtras(bundle);
            setResult(Activity.RESULT_OK, intent);
            finish();
        }
    }
}
