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
    SharedPreferences settings;
    Button mLeft, mForward, mBack, mRight, mStop;
    Intent intentSettings, intentHistory,intentSender;
    Boolean click, velocity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        settings = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        this.intentSender = new Intent(this, Sender_service.class);
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

        //USTAWIENIA
        click = settings.getBoolean("ClickMode",true);
        mBack = (Button) findViewById(R.id.mBack);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            send("Back", -1);

            }
        });

        mForward = (Button) findViewById(R.id.mForward);
        mForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                editor.putString("command","Forward");
//                editor.apply();
//                startActivityForResult(intentSender,1);

                send("Forward",1);

            }
        });
        mLeft = (Button) findViewById(R.id.mLeft);
        mLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                editor.putString("command","Left");
//                editor.apply();
//                startActivityForResult(intentSender,1);
                send("Left",1);

            }
        });
        mRight = (Button) findViewById(R.id.mRight);
        mRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                editor.putString("command","Right");
//                editor.apply();
//                startActivityForResult(intentSender,1);

                send("Right",1);

            }
        });
        mStop = (Button) findViewById(R.id.mStop);
        mStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                editor.putString("command","STOP");
//                editor.apply();
//                startActivityForResult(intentSender,1);
                send("Stop",0);

            }
        });}

        void send(String command, int inte)
    {
        SharedPreferences.Editor editor = settings.edit();
        final int[] speed = {settings.getInt("speed", 0)};
        velocity = settings.getBoolean("VelocityMode", true);

        Log.e("SEND", String.valueOf(velocity));
        Log.e("SEND", String.valueOf(speed[0]));

        if (velocity){

            editor.putString("command",command);
            editor.putInt("speed", speed[0]);
        }
        else {
            speed[0] = speed[0] +inte;
            Log.e("MOJA MILA", String.valueOf(speed[0]));
            editor.putString("command",command);
            editor.putInt("speed", speed[0]);
            editor.putInt("velocity", speed[0]);
        }

        editor.apply();
//        startActivityForResult(intentSender,3);
        Intent intent = new Intent(MainActivity.this, Sender_service.class);
    startService(intent);
    stopService(intent);

    }
    //}


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

}
