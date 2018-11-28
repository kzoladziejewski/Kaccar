package com.example.kacper.kaccar60;


import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Kacper on 22.10.2018.
 */

public class Sending extends AppCompatActivity {


    public void SendData(String ip, String sending, int velocity) throws IOException {
//        SharedPreferences settings;
//        settings = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
//        String ip = settings.getString("mConnectionIP","192.168.1.11");

        String sendingData = "http://"+ip+"/?GET="+sending+"&POST="+velocity;
//        @TODO FINISH A JOB
        Log.e("Failed?", sendingData);
        URL url = new URL(sendingData);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try{
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
        } finally {
            urlConnection.disconnect();
        }
    }
}
