package com.example.kacper.kaccar60;

import android.content.Intent;
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

public class MainActivity extends AppCompatActivity {
    public String mConnectionIP = "192.168.1.5";
    public int velocity = 0;
    String sendingData;
    Button mLeft, mForward, mBack, mRight, mStop, mAccept;
    EditText mConnect;
    SeekBar mVelocitye;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        mConnect = (EditText) findViewById(R.id.editText);
        mStop = (Button) findViewById(R.id.mStop);
        mAccept = (Button) findViewById(R.id.mAccept);
        mVelocitye = (SeekBar) findViewById(R.id.mVolume);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SendData("Back");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        mForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SendData("Forward");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        mLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SendData("Left");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        mRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SendData("Right");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        mStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SendData("STOP");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        mAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mConnectionIP = mConnect.getText().toString();
            }
        });
        mVelocitye.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                velocity = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

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
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.mVelocity)
        {
            startActivity(new Intent(this, Velocity.class));
        }
        if (id == R.id.mIP)
        {
            startActivity(new Intent(this, IPConnector.class));
        }
        if (id == R.id.mHistory)
        {
            startActivity(new Intent(this, History.class));
        }
        if (id == R.id.mControlling)
        {
            startActivity(new Intent(this, Controlling.class));
        }
        return super.onOptionsItemSelected(item);
    }

    public void SendData(String sending) throws IOException {

        sendingData= "http://"+mConnectionIP+"/?GET="+sending+"&POST="+velocity;
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
