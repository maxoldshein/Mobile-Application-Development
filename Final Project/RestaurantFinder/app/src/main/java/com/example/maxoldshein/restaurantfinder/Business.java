package com.example.maxoldshein.restaurantfinder;

import java.io.Serializable;

/**
 * Created by maxoldshein on 5/8/17.
 */

public class Business implements Serializable {
    private String name;
    private String phoneNumber;
    private String address;
    private String zipcode;
    private String type;
    private String style;
    private String delivery;
    private String sundayHours;
    private String mondayHours;
    private String tuesdayHours;
    private String wednesdayHours;
    private String thursdayHours;
    private String fridayHours;
    private String saturdayHours;
    private String description;
    private double latitude;
    private double longitude;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return this.zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStyle() {
        return this.style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getDelivery() {
        return this.delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getSundayHours() {
        return this.sundayHours;
    }

    public void setSundayHours(String sundayHours) {
        this.sundayHours = sundayHours;
    }

    public String getMondayHours() {
        return this.mondayHours;
    }

    public void setMondayHours(String mondayHours) {
        this.mondayHours = mondayHours;
    }

    public String getTuesdayHours() {
        return this.tuesdayHours;
    }

    public void setTuesdayHours(String tuesdayHours) {
        this.tuesdayHours = tuesdayHours;
    }

    public String getWednesdayHours() {
        return this.wednesdayHours;
    }

    public void setWednesdayHours(String wednesdayHours) {
        this.wednesdayHours = wednesdayHours;
    }

    public String getThursdayHours() {
        return this.thursdayHours;
    }

    public void setThursdayHours(String thursdayHours) {
        this.thursdayHours = thursdayHours;
    }

    public String getFridayHours() {
        return this.fridayHours;
    }

    public void setFridayHours(String fridayHours) {
        this.fridayHours = fridayHours;
    }

    public String getSaturdayHours() {
        return this.saturdayHours;
    }

    public void setSaturdayHours(String saturdayHours) {
        this.saturdayHours = saturdayHours;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String toString() {
        return "Name: " + name +
                "\nPhone Number: " + phoneNumber +
                "\nAddress: " + address +
                "\nZipcode: " + zipcode +
                "\nType: " +  type +
                "\nStyle: " +  style +
                "\nDelivery: " + delivery +
                "\nSunday Hours: " + sundayHours +
                "\nMonday Hours: " + mondayHours +
                "\nTuesday Hours: " + tuesdayHours +
                "\nWednesday Hours: " + wednesdayHours +
                "\nThursday Hours: " + thursdayHours +
                "\nFriday Hours: " + fridayHours +
                "\nSaturday Hours: " + saturdayHours +
                "\nDescription: " + description +
                "\nLatitude: " + latitude +
                "\nLongitude: " + longitude;
    }
}