package com.example.kacper.kaccar60;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;

public class Settings extends AppCompatActivity {
    public int aavelocity = 0;
    SeekBar mVelocity;
    Switch mLimit, mMax;
    Boolean mLimitVelo, mMaxVelo, mOneClick, mPressClick;
    Button mAccept;
    EditText mTextEdit;
    public String mConnectionIP = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        mVelocity = (SeekBar) findViewById(R.id.mVelocity);
        final Switch mLimit = (Switch) findViewById(R.id.mLimitVelocity);
        final Switch mMax = (Switch) findViewById(R.id.mMaxVelocity);
        final Switch mOneClicked = (Switch) findViewById(R.id.mOneClick);
        final Switch mPressClicked = (Switch) findViewById(R.id.mPressClick);
        mLimitVelo = mLimit.isChecked();
        mMaxVelo = mMax.isChecked();
        mOneClick = mOneClicked.isChecked();
        mPressClick = mPressClicked.isChecked();
        mAccept = (Button) findViewById(R.id.mAccept);
        mTextEdit = (EditText) findViewById(R.id.mIPConnector);


        SharedPreferences settings;
        settings = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        mLimit.setChecked(settings.getBoolean("LimitVelocity",mLimitVelo));
        mMax.setChecked(settings.getBoolean("MaxVelocity", mMaxVelo));
        mOneClicked.setChecked(settings.getBoolean("OneClick", mOneClick));
        mPressClicked.setChecked(settings.getBoolean("PressClick", mPressClick));
        mTextEdit.setText(settings.getString("mConnectionIP", String.valueOf(mTextEdit.getText())));
        mVelocity.setProgress(settings.getInt("Settings", mVelocity.getProgress()));
        mVelocity.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                                                  @Override
                                                  public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                                      aavelocity = progress;
                                                      getIntent().putExtra("velocity", aavelocity);

                                                  }


                                                  @Override
                                                  public void onStartTrackingTouch(SeekBar seekBar) {

                                                  }

                                                  @Override
                                                  public void onStopTrackingTouch(SeekBar seekBar) {

                                                  }
                                              }
        );



        mLimit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMax.setChecked(!mLimit.isChecked());
                mLimitVelo = mLimit.isChecked();
                mMaxVelo = mMax.isChecked();

            }
        });

        mMax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLimit.setChecked(!mMax.isChecked());
                mLimitVelo = mLimit.isChecked();
                mMaxVelo = mMax.isChecked();

            }
        });

        mOneClicked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPressClicked.setChecked(!mOneClicked.isChecked());
                mOneClick = mOneClicked.isChecked();
                mPressClick = mPressClicked.isChecked();
            }
        });

        mPressClicked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOneClicked.setChecked(!mPressClicked.isChecked());
                mOneClick = mOneClicked.isChecked();
                mPressClick = mPressClicked.isChecked();
            }
        });

    }

    @Override
    protected void onPause()
    {
        super.onPause();


    }
    public int getVelocity()
    {
        return mVelocity.getProgress();

    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        SharedPreferences settings;
        settings = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("OneClick", mOneClick);
        editor.putBoolean("PressClick", mPressClick);
        editor.putBoolean("LimitVelocity",mLimitVelo);
        editor.putBoolean("MaxVelocity", mMaxVelo);
        editor.putInt("Settings", mVelocity.getProgress());
        editor.putString("mConnectionIP",mTextEdit.getText().toString());
        editor.commit();
    }




}
