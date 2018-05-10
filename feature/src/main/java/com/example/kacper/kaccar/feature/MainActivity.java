package com.example.kacper.kaccar.feature;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import com.example.kacper.kaccar.Bluetooth;

public class MainActivity extends AppCompatActivity {
    private Button mForward;
    private Button mBack;
    private Button mLeft;
    private Button mRight;
    private Switch mbluetooth;
    private static final int REQUEST_CONNECT_DEVICE_SECURE = 1;
    private static final int REQUEST_CONNECT_DEVICE_INSECURE = 2;
    private static final int REQUEST_ENABLE_BT = 3;
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
        mbluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               {
                    Changetext();
                    RunBluetooth();
                }
            }
        });
    }

    public void RunBluetooth()
    {        Log.i("No nie działam 1","111");
        // FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Bluetooth bluetooth = new Bluetooth();
        Log.i("No nie działam 2","222");

        // transaction.replace(R.id.frame, bluetooth);
       // transaction.commit();
bluetooth.RunBluetooth();
        Log.i("No nie działam 3","333");

    }

    public void DestroyBluetooth()
    {
    }
    public void Changetext()
    {
        if (mbluetooth.isChecked())
        {
            mbluetooth.setText("ON");
            //RunBluetooth();
        }
        else
        {
            mbluetooth.setText("OFF");
          //  DestroyBluetooth();
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
            Changetext();
            return true;


        }

        return super.onOptionsItemSelected(item);
    }
}
