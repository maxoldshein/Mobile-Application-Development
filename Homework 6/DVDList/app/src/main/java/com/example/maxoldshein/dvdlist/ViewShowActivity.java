package com.example.maxoldshein.dvdlist;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaExtractor;
import android.media.Rating;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ViewShowActivity extends AppCompatActivity {
    private TextView showName;
    private TextView networkName;
    private TextView episodes;
    private RatingBar ratingBar;

    private long currentId;
    private ShowOperations showDbOperations;
    private ListView castList;
    private TextView empty;
    private CustomCastAdapter adapter;
    private Show show;
    private ArrayList<Actor> castMembers;
    private ArrayList<Show> shows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_show);

        showName = (TextView) findViewById(R.id.showName);
        networkName = (TextView) findViewById(R.id.showNetwork);
        episodes = (TextView) findViewById(R.id.showEpisodes);
        ratingBar = (RatingBar) findViewById(R.id.showRating);
        castList = (ListView) findViewById(R.id.showCast);
        empty = (TextView) findViewById(android.R.id.empty);

        initialize();
    }

    public void initialize() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        currentId = bundle.getLong("id");
        String dbName = bundle.getString("dbName");

        showDbOperations = new ShowOperations(this);
        showDbOperations.open();

        shows = (ArrayList) showDbOperations.getAllShows();

        for(int i = 0; i < shows.size(); i++) {
            if(shows.get(i).getId() == currentId) {
                show = shows.get(i);
            }
        }

        if(show != null) {
            showName.setText(show.getName());
            networkName.setText(show.getNetwork());
            episodes.setText(show.getEpisodes() + " episode(s)");
            ratingBar.setRating(Float.valueOf(show.getRating()));
        } else {
            System.out.println("Show not found!");
        }

        castMembers = (ArrayList) showDbOperations.getAllActors(show.getDbName());
        showDbOperations.close();

        adapter = new CustomCastAdapter(this, castMembers);
        castList.setAdapter(adapter);
        castList.setEmptyView(empty);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_view, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();

        if(id == R.id.actionDetails) {
            Intent intent = new Intent(ViewShowActivity.this, EditShowActivity.class);
            Bundle bundle = new Bundle();
            bundle.putLong("id", show.getId());
            bundle.putString("dbName", show.getDbName());
            intent.putExtras(bundle);
            startActivityForResult(intent, 103);
            return true;
        }

        if(id == R.id.actionCast) {
            Intent intent = new Intent(ViewShowActivity.this, EditCastActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("dbName", show.getDbName());
            intent.putExtras(bundle);
            startActivityForResult(intent, 104);
            return true;
        }

        return super.onOptionsItemSelected(menuItem);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            if((requestCode == 103) && (resultCode == Activity.RESULT_OK)) {
                initialize();
            }

            if((requestCode == 104) && (resultCode == Activity.RESULT_OK)) {
                initialize();
            }
        } catch(Exception e) {
            System.out.println("Problems -- " + requestCode + " " + resultCode);
        }
    }
}
