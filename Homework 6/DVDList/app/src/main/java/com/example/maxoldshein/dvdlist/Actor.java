package com.example.maxoldshein.dvdlist;

/**
 * Created by maxoldshein on 5/1/17.
 */

public class Actor {
    private int id;
    private String name;
    private String character;

    public Actor(String name, String character) {
        this.name = name;
        this.character = character;
    }

    public Actor() {

    }

    public long getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharacter() {
        return this.character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String toString() {
        return this.name;
    }
}
