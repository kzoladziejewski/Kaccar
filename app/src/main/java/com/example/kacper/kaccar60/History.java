package com.example.kacper.kaccar60;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Set;

public class History extends MainActivity {

    Button mRefresh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Intent intent = new Intent(this, History_service.class);
        startService(intent);
        stopService(intent);
        SharedPreferences settings;
        settings = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String list = settings.getString("data", "None");
        Log.e("aaa", String.valueOf(list));
    }
}

