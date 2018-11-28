package com.example.kacper.kaccar60;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.Socket;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class MainActivity extends AppCompatActivity {

    String sendingData;
    Button mLeft, mForward, mBack, mRight, mStop;
    Intent intentSettings, intentHistory;
    public int velocity= 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.intentSettings = new Intent(this, Settings.class);
        this.intentHistory = new Intent(this, History.class);

        setContentView(R.layout.activity_main);
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        mLeft = (Button) findViewById(R.id.mLeft);
        mRight = (Button) findViewById(R.id.mRight);
        mForward = (Button) findViewById(R.id.mForward);
        mBack = (Button) findViewById(R.id.mBack);
        mStop = (Button) findViewById(R.id.mStop);

        //getIntent().putExtra("ipConnector", "?");
        //getIntent().putExtra("velocity", "??");
        SharedPreferences settings;
        settings = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        Log.e("MAIN BO MAIN", ip);
        final Sending sending = new Sending();

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendMesage(sending,"back");

            }
        });

        mForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMesage(sending,"forward");

            }
        });
        mLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMesage(sending,"left");
            }
        });
        mRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMesage(sending,"right");


            }
        });
        mStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMesage(sending,"stop");

            }
        });






    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.commonmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {int id = item.getItemId();
        if (id == R.id.mSettings)
        {
            startActivityForResult(intentSettings,1);
        }

        if (id == R.id.mHistory)
        {
            startActivityForResult(intentHistory,1);

        }
        return super.onOptionsItemSelected(item);
    }

    public void sendMesage(Sending sending,String mesage){
        String ip = settings.getString("mConnectionIP","192.168.1.11");

        try {
            sending.SendData("aaaa","Right",velocity);
        } catch (IOException e) {
            e.printStackTrace();
        }}
}
