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
import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class MainActivity extends AppCompatActivity {

    String sendingData;
    Button mLeft, mForward, mBack, mRight, mStop;
    Intent intentVelocity, intentIPConnector,intentHistory,intentControlling;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Log.e("MAIN ACTIVITY", "ON CREATE WYWOLANE");
        this.intentVelocity = new Intent(this, Velocity.class);
        this.intentIPConnector = new Intent(this, IPConnector.class);
        this.intentHistory = new Intent(this, History.class);
        this.intentControlling = new Intent(this, Controlling.class);
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
        getIntent().putExtra("ipConnector", "dupa");
        getIntent().putExtra("velocity", "dupa");

        final Sending sending = new Sending();
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    sending.SendData("Back");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        mForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    sending.SendData("Forward");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        mLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    sending.SendData("Left");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        mRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    sending.SendData("Right");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        mStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    sending.SendData("STOP");
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
        if (id == R.id.mVelocity)
        {
            startActivityForResult(intentVelocity,1);
//            startActivity(new Intent(this, Velocity.class));
        }
        if (id == R.id.mIP)
        {
//            startActivity(new Intent(this, IPConnector.class));
            startActivityForResult(intentIPConnector,1);

        }
        if (id == R.id.mHistory)
        {
//            startActivity(new Intent(this, History.class));
//            startActivityForResult(intentHistory,1);

        }
        if (id == R.id.mControlling)
        {
//            startActivity(new Intent(this, Controlling.class));
//            startActivityForResult(intentControlling,1);

        }
        return super.onOptionsItemSelected(item);
    }


}
