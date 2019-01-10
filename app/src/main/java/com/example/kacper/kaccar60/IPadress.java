package com.example.kacper.kaccar60;

/**
 * Created by Kacper on 10.01.2019.
 */

public class IPadress {
    String ip;
    int color;

    public IPadress(String s)
    {
        ip = s;
        color = 0xFF00FF00;
    }

    public String getIPAdress()
    {
        return ip;
    }
    public int getColor()
    {
        return color;
    }
}
