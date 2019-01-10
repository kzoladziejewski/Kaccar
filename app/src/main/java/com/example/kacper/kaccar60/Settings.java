package com.example.kacper.kaccar60;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;

public class Settings extends AppCompatActivity {
    SeekBar mVelocity;
    Switch mClick,mVelocityClick;
    SharedPreferences settings;
    EditText mIPConnector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        settings = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        final SharedPreferences.Editor editor = settings.edit();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        mVelocity = (SeekBar) findViewById(R.id.mVelocity);
        mVelocity.setProgress(settings.getInt("Progress_velocity",50));

        mClick = (Switch) findViewById(R.id.mPressClick);
        mClick.setChecked(settings.getBoolean("ClickMode",true));

        mVelocityClick = (Switch) findViewById(R.id.mVelocityClick);
        mVelocityClick.setChecked(settings.getBoolean("VelocityMode",true));
        mVelocityClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mVelocityClick.isChecked()){

                    editor.putInt("velocity", mVelocity.getProgress());
                    editor.putInt("Progress_velocity", mVelocity.getProgress());
                    editor.apply();
                }
                else {

                }
            }
        });
        mIPConnector = (EditText) findViewById(R.id.mIPConnector);
        String ipcon = settings.getString("ipconnect",getResources().getString(R.string.IPConnect));
        mIPConnector.setText(ipcon);

        mVelocity.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                                                  @Override
                                                  public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                                      editor.putInt("velocity", progress);
                                                      editor.apply();
                                                  }


                                                  @Override
                                                  public void onStartTrackingTouch(SeekBar seekBar) {

                                                  }

                                                  @Override
                                                  public void onStopTrackingTouch(SeekBar seekBar) {
                                                  }
                                              }
        );



    }

    @Override
    protected void onPause()
    {
        super.onPause();


    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        settings = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("velocity", mVelocity.getProgress());
        editor.putInt("Progress_velocity", mVelocity.getProgress());
        editor.putString("ipconnect", String.valueOf(mIPConnector.getText()));
        editor.putBoolean("VelocityMode", mVelocityClick.isChecked());
        editor.putBoolean("ClickMode", mClick.isChecked());
//        editor.putInt("speed", 0);

        Log.e("VelocityMode", String.valueOf(mVelocityClick.isChecked()));
        Log.e("ClickMode", String.valueOf(mClick.isChecked()));
        editor.apply();
    }




}
