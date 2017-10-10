package com.example.maxoldshein.restaurantfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SearchByZipcodeActivity extends AppCompatActivity {
    private EditText sbzEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_zipcode);

        sbzEditText = (EditText) findViewById(R.id.sbzEditText);
    }

    public void searchByZipcode(View view) {
        String searchZipcode = sbzEditText.getText().toString();

        if(searchZipcode.equals("")) {
            Toast.makeText(this, "Enter a query before you search!", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(getApplicationContext(), DisplaySearchByZipcodeActivity.class);
            intent.putExtra("searchZipcode", searchZipcode);
            startActivity(intent);
        }
    }
}
