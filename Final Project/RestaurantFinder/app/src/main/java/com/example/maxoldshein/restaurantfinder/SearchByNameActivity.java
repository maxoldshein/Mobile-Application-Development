package com.example.maxoldshein.restaurantfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SearchByNameActivity extends AppCompatActivity {
    private EditText sbnEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_name);

        sbnEditText = (EditText) findViewById(R.id.sbnEditText);
    }

    public void searchByName(View view) {
        String searchName = sbnEditText.getText().toString();

        if(searchName.equals("")){
            Toast.makeText(this, "Enter a query before you search!", Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(getApplicationContext(), DisplaySearchByNameActivity.class);
            intent.putExtra("searchName", searchName);
            startActivity(intent);
        }
    }
}
