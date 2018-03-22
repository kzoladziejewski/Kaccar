package com.example.kacper.kaccar.feature;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Switch;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    private Button mForward;
    private Button mBack;
    private Button mLeft;
    private Button mRight;
    private Switch mbluetooth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mForward = (Button) findViewById(R.id.Forward);
        mBack = (Button) findViewById(R.id.Back);
        mLeft = (Button) findViewById(R.id.Left);
        mRight = (Button) findViewById(R.id.Right);
        mbluetooth = (Switch) findViewById(R.id.Bluetooth);
        mbluetooth
    }

    //@Override
    public void Changetext()
    {
        if (mbluetooth.isChecked()==false){
            mbluetooth.setTextOn("eee");
    }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;


        }

        return super.onOptionsItemSelected(item);
    }
}
