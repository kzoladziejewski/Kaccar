package com.example.kacper.kaccar60;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Sender_service extends Service {
    public Sender_service() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    @Override
    public void onCreate() {

        super.onCreate();
        try {
            sendData();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                finish();
//            }
//        },100);
    }

    public void sendData() throws Exception
    {
        SharedPreferences settings;
        String ip, command;
        int velocity;
        settings = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        ip = settings.getString("ipconnect","192.168.100.1");
        command = settings.getString("command","STOP");
        velocity = settings.getInt("velocity",50);
//        URL url = new URL("http://156.17.148.88/");
        URL url = new URL("http://"+ip+"/?GET="+command+"&POST="+velocity);
        Log.e("Sender", String.valueOf(url));
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try{
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            int a = urlConnection.getResponseCode();
            String ab = urlConnection.getResponseMessage();
        }
        finally {
            urlConnection.disconnect();
        }
    }





}
