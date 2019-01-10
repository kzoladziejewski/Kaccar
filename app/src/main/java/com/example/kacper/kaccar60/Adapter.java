package com.example.kacper.kaccar60;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Kacper on 10.01.2019.
 */

public class Adapter extends RecyclerView.Adapter {
    private ArrayList<IPadress> IPs = new ArrayList<IPadress>();
    private RecyclerView mRecyclerView;
    Context ctx;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    private class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitle;

        public MyViewHolder(View pItem) {
            super(pItem);
            mTitle = (TextView) pItem.findViewById(R.id.ip_title);
        }}

    public Adapter(ArrayList<IPadress> pIPs, RecyclerView pRecyclerView, Context context){
        IPs = pIPs;
        mRecyclerView = pRecyclerView;
        ctx = context;
        preferences = ctx.getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        editor = preferences.edit();

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_layout, parent, false);
        //        return null;

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = mRecyclerView.getChildAdapterPosition(v);
                String a = preferences.getString("ipconnect","dupa");
                Log.e("Dupcia",a);
                editor.putString("ipconnect",IPs.get(pos).getIPAdress());
                editor.apply();
                a  = preferences.getString("ipconnect","dupa");
                Log.e("Dupcia",a);
                Toast toast = Toast.makeText(ctx, IPs.get(pos).getIPAdress(), Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
IPadress iPadress = IPs.get(position);
        ((MyViewHolder) holder).mTitle.setText(iPadress.getIPAdress());
        ((MyViewHolder) holder).mTitle.setBackgroundColor(iPadress.getColor());
    }

    @Override
    public int getItemCount() {

        return IPs.size();
    }





}
