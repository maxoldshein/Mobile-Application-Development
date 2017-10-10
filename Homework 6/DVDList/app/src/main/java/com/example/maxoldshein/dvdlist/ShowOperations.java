package com.example.maxoldshein.dvdlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maxoldshein on 5/1/17.
 */

public class ShowOperations {
    private DatabaseWrapper databaseHelper;
    private String[] SHOW_TABLE_COLUMNS = {DatabaseWrapper.SHOW_ID, DatabaseWrapper.SHOW_NAME, DatabaseWrapper.SHOW_NETWORK, DatabaseWrapper.SHOW_RATING, DatabaseWrapper.SHOW_EPISODES, DatabaseWrapper.CAST_DB};
    private String[] ACTOR_TABLE_COLUMNS = {DatabaseWrapper.ACTOR_ID, DatabaseWrapper.ACTOR_NAME, DatabaseWrapper.ACTOR_CHARACTER};
    SQLiteDatabase database;

    public ShowOperations(Context context) {
        databaseHelper = new DatabaseWrapper(context);
    }

    public void open() throws SQLException{
        database = databaseHelper.getWritableDatabase();
    }

    public void close() {
        databaseHelper.close();
    }

    public void createTable(String tableName) {
        databaseHelper.createTable(database, tableName);
    }

    public Show addShow(String name, String network) {
        String dbName = name.replaceAll("\\s", "");

        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseWrapper.SHOW_NAME, name);
        contentValues.put(DatabaseWrapper.SHOW_NETWORK, network);
        contentValues.put(DatabaseWrapper.SHOW_RATING, String.valueOf(0));
        contentValues.put(DatabaseWrapper.SHOW_EPISODES, 0);
        contentValues.put(DatabaseWrapper.CAST_DB, dbName + "_db");

        long showId = database.insert(DatabaseWrapper.SHOWS, null, contentValues);

        Cursor cursor = database.query(DatabaseWrapper.SHOWS, SHOW_TABLE_COLUMNS, DatabaseWrapper.SHOW_ID + " = " + showId, null, null, null, null);

        cursor.moveToFirst();

        Show newComment = parseShow(cursor);
        cursor.close();
        return newComment;
    }

    public void updateShow(Show comment) {
        comment.setDbName(comment.getName().replaceAll("\\s", "") + "_db");

        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseWrapper.SHOW_NAME, comment.getName());
        contentValues.put(DatabaseWrapper.SHOW_NETWORK, comment.getNetwork());
        contentValues.put(DatabaseWrapper.SHOW_RATING, comment.getRating());
        contentValues.put(DatabaseWrapper.SHOW_EPISODES, comment.getEpisodes());
        contentValues.put(DatabaseWrapper.CAST_DB, comment.getDbName());

        database.update(DatabaseWrapper.SHOWS, contentValues, DatabaseWrapper.SHOW_ID + " = ?", new String[] {String.valueOf(comment.getId())});

        System.out.println("Updated show in the database.");
    }

    public void deleteShow(Show comment) {
        long id = comment.getId();
        database.delete(DatabaseWrapper.SHOWS, DatabaseWrapper.SHOW_ID + " = " + id, null);

        System.out.println("Deleted show with id " + id);
    }

    public List getAllShows() {
        List shows = new ArrayList();

        Cursor cursor = database.query(DatabaseWrapper.SHOWS, SHOW_TABLE_COLUMNS, null, null, null, null, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
            Show show = parseShow(cursor);
            shows.add(show);
            cursor.moveToNext();
        }

        cursor.close();
        return shows;
    }

    private Show parseShow(Cursor cursor) {
        System.out.println(cursor.toString());
        Show show = new Show();
        show.setId(cursor.getInt(0));
        show.setName(cursor.getString(1));
        show.setNetwork(cursor.getString(2));
        show.setRating(cursor.getString(3));
        show.setEpisodes(cursor.getInt(4));
        show.setDbName(cursor.getString(5));
        return show;
    }

    public Actor addActor(String name, String character, String tableName) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseWrapper.ACTOR_NAME, name);
        contentValues.put(DatabaseWrapper.ACTOR_CHARACTER, character);

        long actorId = database.insert(tableName, null, contentValues);

        Cursor cursor = database.query(tableName, ACTOR_TABLE_COLUMNS, DatabaseWrapper.ACTOR_ID + " = " + actorId, null, null, null, null);
        cursor.moveToFirst();

        Actor newComment = parseActor(cursor);
        cursor.close();
        return newComment;
    }

    public void deleteActor(Actor comment, String tableName) {
        long actorId = comment.getId();
        database.delete(tableName, DatabaseWrapper.ACTOR_ID + " = " + actorId, null);

        System.out.println("Deleted actor with id " + actorId);
    }

    public List getAllActors(String tableName) {
        List actors = new ArrayList();

        Cursor cursor = database.query(tableName, ACTOR_TABLE_COLUMNS, null, null, null, null, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
            Actor actor = parseActor(cursor);
            actors.add(actor);
            cursor.moveToNext();
        }

        cursor.close();
        return actors;
    }

    public Actor parseActor(Cursor cursor) {
        Actor actor = new Actor();
        actor.setId(cursor.getInt(0));
        actor.setName(cursor.getString(1));
        actor.setCharacter(cursor.getString(2));
        return actor;
    }

}
