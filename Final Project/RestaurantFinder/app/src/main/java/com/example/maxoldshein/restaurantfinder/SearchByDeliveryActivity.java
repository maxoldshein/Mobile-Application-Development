package com.example.maxoldshein.restaurantfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SearchByDeliveryActivity extends AppCompatActivity {
    private RadioGroup sbdRadioDelivery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_delivery);

        sbdRadioDelivery = (RadioGroup) findViewById(R.id.sbdRadioDelivery);
    }

    public void searchByDelivery(View view) {
        int selectedId = sbdRadioDelivery.getCheckedRadioButtonId();

        RadioButton checkedRadioButton = (RadioButton) findViewById(selectedId);

        String searchDelivery = checkedRadioButton.getText().toString();

        Intent intent = new Intent(getApplicationContext(), DisplaySearchByDeliveryActivity.class);
        intent.putExtra("searchDelivery", searchDelivery);
        startActivity(intent);
    }
}
