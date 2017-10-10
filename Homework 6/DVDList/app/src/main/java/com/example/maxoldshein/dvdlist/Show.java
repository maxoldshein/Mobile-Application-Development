package com.example.maxoldshein.dvdlist;

/**
 * Created by maxoldshein on 5/1/17.
 */

public class Show extends Object {
    private int id;
    private String name;
    private String network;
    private String dbName;
    private int episodes;
    private String rating;

    public Show(String name, String network) {
        this.name = name;
        this.network = network;
    }

    public Show() {

    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public int getEpisodes() {
        return episodes;
    }

    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String toString() {
        return name + "\n" + network;
    }
}
