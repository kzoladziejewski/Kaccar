package com.example.kacper.kaccar60;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.os.IBinder;
import android.util.Log;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class History_service extends Service {
    List<String> udane_ip = new ArrayList<>();

    public History_service() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        SharedPreferences settings;
        settings = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        WifiManager mWifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        long bramka = mWifiManager.getDhcpInfo().gateway;
        String bramka_ip = String.format(
                "%d.%d.%d",
                (bramka & 0xff),
                (bramka >> 8 & 0xff),
                (bramka >> 16 & 0xff));
        for (int i =1; i < 256;i++)
        {
            String ip=bramka_ip+"."+i;
            try {
                if(InetAddress.getByName(ip).isReachable(25)){
                    udane_ip.add(ip);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        editor.putString("data", String.valueOf(udane_ip));
        editor.apply();
    }


}
