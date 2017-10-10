package com.example.maxoldshein.dvdlist;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import java.util.ArrayList;

public class EditShowActivity extends AppCompatActivity {
    private TextView showName;
    private TextView networkName;
    private TextView episodes;
    private RatingBar ratingBar;

    private long currentId;
    private ShowOperations showDbOperations;
    private Show show;
    private ArrayList<Show> shows;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_show);

        initialize();
    }

    public void initialize() {
        showName = (TextView) findViewById(R.id.esName);
        networkName = (TextView) findViewById(R.id.esNetwork);
        episodes = (TextView) findViewById(R.id.esEpisodes);
        ratingBar = (RatingBar) findViewById(R.id.esRating);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        currentId = bundle.getLong("id");
        String dbName = bundle.getString("dbName");

        showDbOperations = new ShowOperations(this);
        showDbOperations.open();
        shows = (ArrayList) showDbOperations.getAllShows();
        showDbOperations.close();

        for(int i = 0; i < shows.size(); i++) {
            if(shows.get(i).getId() == currentId) {
                show = shows.get(i);
            }
        }

        if(show != null) {
            showName.setText(show.getName());
            networkName.setText(show.getNetwork());
            episodes.setText(String.valueOf(show.getEpisodes()));
            ratingBar.setRating(Float.valueOf(show.getRating()));
        }
    }

    public void esEditShow(View button) {
        Show show = new Show();
        String name = showName.getText().toString();
        String network = networkName.getText().toString();
        String showEpisodes = episodes.getText().toString();
        float rating = ratingBar.getRating();

        show.setId((int) currentId);
        show.setName(name);
        show.setNetwork(network);
        if(!showEpisodes.equals("")) {
            show.setEpisodes(Integer.parseInt(showEpisodes));
        }
        show.setRating(String.valueOf(rating));

        showDbOperations.open();
        showDbOperations.updateShow(show);
        showDbOperations.close();

        Intent intent = getIntent();
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}
