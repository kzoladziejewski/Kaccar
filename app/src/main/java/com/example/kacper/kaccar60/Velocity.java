package com.example.kacper.kaccar60;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Switch;

public class Velocity extends MainActivity {
    public int aavelocity = 0;
    SeekBar mVelocitye;
    Switch mLimit, mMax;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_velocity);
        mVelocitye = (SeekBar) findViewById(R.id.mVolume);
        final Switch mLimit = (Switch) findViewById(R.id.mLimitVelocity);
        final Boolean mLimitVelo = mLimit.isChecked();
        final Switch mMax = (Switch) findViewById(R.id.mMaxVelocity);
        final Boolean mMaxVelo = mMax.isChecked();
    mVelocitye.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            aavelocity = progress;
            Log.e("Predkosc", String.valueOf(aavelocity));

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

        }
    });

    mMax.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mLimit.setChecked(!mMax.isChecked());

        }
    });
    }
}
