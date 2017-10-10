package com.example.maxoldshein.restaurantfinder;

import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

import cz.msebera.android.httpclient.*;
import cz.msebera.android.httpclient.client.*;
import cz.msebera.android.httpclient.impl.client.*;
import cz.msebera.android.httpclient.client.methods.*;

/**
 * Created by maxoldshein on 5/8/17.
 */

public class JSONFunctions {
    public static JSONObject getJSONFromURL(String argumentURL) {
        //System.out.println("In getJSONFromURL");
        System.out.println("URL: " + argumentURL);
        InputStream inputStream = null;
        String result = "";
        JSONObject jsonArray = null;

        //Download the JSON data from the URL.
        try {
            //System.out.println("In first try.");
            URL url = new URL(argumentURL);
            URLConnection urlConnection = url.openConnection();
            inputStream = urlConnection.getInputStream();
        } catch (Exception e) {
            Log.e("log_tag", "Error in http connection " + e.toString());
        }

        //Convert the response to a String
        try {
            //System.out.println("In second try.");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"), 8);
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;

            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }

            inputStream.close();
            result = stringBuilder.toString();
            //System.out.println(result);
        } catch (Exception e) {
            Log.e("log_tag", "Error converting result " + e.toString());
        }

        try {
            //System.out.println("In third try.");
            jsonArray = new JSONObject(result);
        } catch (Exception e) {
            Log.e("log_tag", "Error parsing data " + e.toString());
        }

        return jsonArray;
    }
}
