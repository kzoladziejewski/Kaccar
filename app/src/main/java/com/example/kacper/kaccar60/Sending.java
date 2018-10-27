package com.example.kacper.kaccar60;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Kacper on 22.10.2018.
 */

public class Sending extends AppCompatActivity {
//    String sengingData = "http://192.168.1.11";

//    String ipConnector = getIntent().getStringExtra("ipConnector");

    public void SendData(String sending) throws IOException {

//        String sendingData= "http://"+ip+"/?GET="+sending+"&POST="+velocity;
        String sendingData = "FIND ANY WAY!";
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
