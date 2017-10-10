package com.example.maxoldshein.earthquaketracker;

import java.io.Serializable;

/**
 * Created by maxoldshein on 5/2/17.
 */

public class Earthquake implements Serializable {
    private String date;
    private double depth;
    private String earthquakeId;
    private String earthquakeSource;
    private double latitude;
    private double longitude;
    private double magnitude;

    public Earthquake() {

    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getDepth() {
        return this.depth;
    }

    public void setDepth(double depth) {
        this.depth = depth;
    }

    public String getEarthquakeId() {
        return this.earthquakeId;
    }

    public void setEarthquakeId(String earthquakeId) {
        this.earthquakeId = earthquakeId;
    }

    public String getEarthquakeSource() {
        return this.earthquakeSource;
    }

    public void setEarthquakeSource(String earthquakeSource) { this.earthquakeSource = earthquakeSource; }

    public double getLatitude() { return this.latitude; }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getMagnitude() {
        return this.magnitude;
    }

    public void setMagnitude(double magnitude) {
        this.magnitude = magnitude;
    }

    public String toString() {
        return "Date: " + date + "\nDepth: " + depth + "\nEarthquakeId: " + earthquakeId + "\nEarthquakeSource: " + earthquakeSource + "\nLatitude: "  + latitude + "\nLongitude: " + longitude + "\nMagnitude: " + magnitude;
    }
}
