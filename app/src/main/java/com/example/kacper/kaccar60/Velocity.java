package com.example.kacper.kaccar60;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Switch;

public class Velocity extends AppCompatActivity {
    public int aavelocity = 0;
    SeekBar mVelocitye;
    Switch mLimit, mMax;
    Boolean mLimitVelo, mMaxVelo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_velocity);
        mVelocitye = (SeekBar) findViewById(R.id.mVolume);
        final Switch mLimit = (Switch) findViewById(R.id.mLimitVelocity);
        final Switch mMax = (Switch) findViewById(R.id.mMaxVelocity);
        mLimitVelo = mLimit.isChecked();
        mMaxVelo = mMax.isChecked();
        SharedPreferences settings;
        settings = getSharedPreferences("Velocity", Activity.MODE_PRIVATE);
        mLimit.setChecked(settings.getBoolean("LimitVelocity",mLimitVelo));
        mMax.setChecked(settings.getBoolean("MaxVelocity", mMaxVelo));
        mVelocitye.setProgress(settings.getInt("Velocity", mVelocitye.getProgress()));
    mVelocitye.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
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
    }
    @Override
    protected void onPause()
    {
        super.onPause();


    }
    public int getVelocity()
    {
        return mVelocitye.getProgress();

    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        SharedPreferences settings;
        settings = getSharedPreferences("Velocity", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();

        editor.putBoolean("LimitVelocity",mLimitVelo);
        editor.putBoolean("MaxVelocity", mMaxVelo);
        editor.putInt("Velocity", mVelocitye.getProgress());
        editor.commit();
    }


}
