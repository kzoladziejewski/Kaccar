package com.example.kacper.kaccar60;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class History extends MainActivity {
    RecyclerView mLists;
    Button mRefresh;
    ArrayList<IPadress> iPadresses = new ArrayList<>();
    ArrayList<String> ipadr = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        mLists = (RecyclerView) findViewById(R.id.mIPlist);
        mLists.setHasFixedSize(true);
        mLists.setLayoutManager(new LinearLayoutManager(this));
        mLists.setItemAnimator(new DefaultItemAnimator());
        mRefresh = (Button) findViewById(R.id.mRefresh);
        mRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRestartRefresh();
            }
        });


    }

    public void mRestartRefresh()
    {
        Intent intent = new Intent(this, History_service.class);
        startService(intent);
        stopService(intent);
        SharedPreferences settings;
        settings = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String list = settings.getString("data", "None");
        list = list.replace("[", "").replace("]", "").replace("'", "");
        List<String> items = Arrays.asList(list.split("\\n*,\\s*"));
        for (int i = 0; i < items.size(); ++i) {
            if(!ipadr.contains(items.get(i)))
            {iPadresses.add(new IPadress(items.get(i)));
        ipadr.add(items.get(i));
            }
        }
        mLists.setAdapter(new Adapter(iPadresses, mLists, getApplicationContext()));

    }
}

