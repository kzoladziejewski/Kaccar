package com.example.kacper.kaccar;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.kacper.kaccar.feature.MainActivity;

/**
 * Created by Kacper on 22.03.2018.
 */



public class Bluetooth extends MainActivity {
    private BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

    private static final int REQUEST_CONNECT_DEVICE_SECURE = 1;
    private static final int REQUEST_CONNECT_DEVICE_INSECURE = 2;
    private static final int REQUEST_ENABLE_BT = 3;

@Override
public void onCreate(Bundle savedInstanceState)
{
    super.onCreate(savedInstanceState);
    setContentView(R.layout.support_simple_spinner_dropdown_item);
//
}


    @Override
    public void RunBluetooth(){

 //TODO cokolwiek
    if (!mBluetoothAdapter.isEnabled()) {
        Log.i("No nie działam 4","444");

        Intent enableBtIntent = new Intent(mBluetoothAdapter.ACTION_REQUEST_ENABLE);
        Log.i("No nie działam 5","555");

    startActivityForResult(enableBtIntent,1);
        Log.i("No nie działam 6","666");

    }

}
}

