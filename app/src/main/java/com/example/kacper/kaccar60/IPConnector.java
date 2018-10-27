package com.example.kacper.kaccar60;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class IPConnector extends AppCompatActivity {

    Button mConnect;
    EditText mTextEdit;
    public String mConnectionIP = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ipconnector);
        mConnect = (Button) findViewById(R.id.mAccept);
        mTextEdit = (EditText) findViewById(R.id.editText);
        SharedPreferences settings;
        settings = getSharedPreferences("IPConnectorSettings", Activity.MODE_PRIVATE);
        mTextEdit.setText(settings.getString("mConnectionIP", String.valueOf(mTextEdit.getText())));
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        super.onRestoreInstanceState(savedInstanceState);
        String stateSaved = savedInstanceState.getString("saved_state");
        mTextEdit.setText(stateSaved);
    }





    @Override
    protected void onSaveInstanceState(Bundle outSavedInstanceState) {
        super.onSaveInstanceState(outSavedInstanceState);
        outSavedInstanceState.putString("saved_state",mTextEdit.getText().toString());
    }

    public String getIP()
    {
        return mTextEdit.getText().toString();
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        SharedPreferences settings;
        settings = getSharedPreferences("IPConnectorSettings", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("mConnectionIP",mTextEdit.getText().toString());
        editor.commit();

    }



}
