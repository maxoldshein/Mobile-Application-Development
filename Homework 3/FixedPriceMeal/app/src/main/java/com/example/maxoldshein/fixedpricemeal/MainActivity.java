//Maxwell Oldshein
//Mobile Application Development
//Professor Signorile
//Homework 3 - Fixed Price Meal

package com.example.maxoldshein.fixedpricemeal;

import android.app.Activity;
import android.app.ApplicationErrorReport;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button appetizerCourseButton;
    Button pastaCourseButton;
    Button meatFishCourseButton;
    Button dessertCourseButton;
    Button clearAllChoicesButton;
    Button confirmOrderButton;

    TextView appetizerText;
    TextView pastaText;
    TextView meatFishText;
    TextView dessertText;

    EditText customerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appetizerCourseButton = (Button) findViewById(R.id.appetizerCourseButton);
        pastaCourseButton = (Button) findViewById(R.id.pastaCourseButton);
        meatFishCourseButton = (Button) findViewById(R.id.meatFishCourseButton);
        dessertCourseButton = (Button) findViewById(R.id.dessertCourseButton);
        clearAllChoicesButton = (Button) findViewById(R.id.clearAllChoicesButton);
        confirmOrderButton = (Button) findViewById(R.id.confirmOrderButton);

        appetizerText = (TextView) findViewById(R.id.appetizerText);
        pastaText = (TextView) findViewById(R.id.pastaText);
        meatFishText = (TextView) findViewById(R.id.meatFishText);
        dessertText = (TextView) findViewById(R.id.dessertText);

        customerName = (EditText) findViewById(R.id.customerNameField);
    }

    private void clearText() {
        appetizerText.setText("Appetizer: ");
        pastaText.setText("First Course: ");
        meatFishText.setText("Second Course: ");
        dessertText.setText("Dessert Course: ");
        customerName.setText("");
    }

    public void onCourseClick(View clickedButton) {
        int id = clickedButton.getId();

        switch(id) {
            case R.id.appetizerCourseButton:
                Intent appetizerIntent = new Intent(this, AppetizerActivity.class);
                Bundle appetizerData = new Bundle();
                appetizerIntent.putExtras(appetizerData);
                startActivityForResult(appetizerIntent, 100);
                break;
            case R.id.pastaCourseButton:
                Intent pastaIntent = new Intent(this, PastaActivity.class);
                Bundle pastaData = new Bundle();
                pastaIntent.putExtras(pastaData);
                startActivityForResult(pastaIntent, 101);
                break;
            case R.id.meatFishCourseButton:
                Intent meatFishIntent = new Intent(this, MeatFishActivity.class);
                Bundle meatFishData = new Bundle();
                meatFishIntent.putExtras(meatFishData);
                startActivityForResult(meatFishIntent, 102);
                break;
            case R.id.dessertCourseButton:
                Intent dessertIntent = new Intent(this, DessertActivity.class);
                Bundle dessertData = new Bundle();
                dessertIntent.putExtras(dessertData);
                startActivityForResult(dessertIntent, 103);
                break;
            default:
                break;
        }
    }

    public void onClearAllChoicesClick(View clickedButton) {
        clearText();
    }

    public void onConfirmOrderClick(View clickedButton) {
        String text =
                "Order Placed!\nName: " + customerName.getText() + "\n" +
                appetizerText.getText() + "\n" +
                pastaText.getText() + "\n" +
                meatFishText.getText() + "\n" +
                dessertText.getText();
        if (customerName.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Don't forget the customers name!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
            clearText();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            if ((requestCode == 100) && (resultCode == Activity.RESULT_OK)) {
                Bundle results = data.getExtras();
                String choice = results.getString("appetizer");
                appetizerText.setText("Appetizer: " + choice);
            }

            if ((requestCode == 101) && (resultCode == Activity.RESULT_OK)) {
                Bundle results = data.getExtras();
                String choice = results.getString("pasta");
                pastaText.setText("First Course: " + choice);
            }

            if ((requestCode == 102) && (resultCode == Activity.RESULT_OK)) {
                Bundle results = data.getExtras();
                String choice = results.getString("meatFish");
                meatFishText.setText("Second Course: " + choice);
            }

            if ((requestCode == 103) && (resultCode == Activity.RESULT_OK)) {
                Bundle results = data.getExtras();
                String choice = results.getString("dessert");
                dessertText.setText("Dessert: " + choice);
            }
        } catch (Exception e) {
            appetizerText.setText("Problems -- " + requestCode + " " + resultCode);
            pastaText.setText("Problems -- " + requestCode + " " + resultCode);
            meatFishText.setText("Problems -- " + requestCode + " " + resultCode);
            dessertText.setText("Problems -- " + requestCode + " " + resultCode);
        }
    }
}
