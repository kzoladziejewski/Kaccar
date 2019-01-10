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

    }

    public void sendData() throws Exception
    {
        SharedPreferences settings;
        String ip, command;
        int velocity;
        boolean stop;
        settings = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        ip = settings.getString("ipconnect","192.168.100.1");
        command = settings.getString("command","Stop");
        stop = settings.getBoolean("finish",false);
        velocity = settings.getInt("velocity",50);
        URL url = new URL("http://"+ip+"/?GET="+command+"&POST="+velocity);
        Log.e("Sender", String.valueOf(url));
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try{
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
        }
        finally {
            urlConnection.disconnect();
        }

        if (stop)
        {
            Log.e("STO", "MAM BLOKOWAC SERWIS");
             url = new URL("http://"+ip+"/?GET="+"Stop"+"&POST="+velocity);
             urlConnection = (HttpURLConnection) url.openConnection();
            try{
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            }
            finally {
                urlConnection.disconnect();
            }

        }
    }
}
