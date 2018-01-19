package com.example.maxoldshein.smarttipper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button getStartedButton = (Button) findViewById(R.id.getStartedButton);
        getStartedButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Button was clicked, going to the next activity!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, SmartCalculator.class);
                startActivity(intent);
            }
        });

        Spinner stateSelectSpinner = (Spinner) findViewById(R.id.stateSelectSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.statesArray, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stateSelectSpinner.setAdapter(adapter);
    }
}
