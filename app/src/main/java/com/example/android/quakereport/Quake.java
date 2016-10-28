package com.example.android.quakereport;

/**
 * Created by Student on 03/10/2016.
 */

public class Quake {


    private String mUrl;
    private Double mMagnitude;
    private String mLocation;
    private Long mMilisecondDate;



    public Quake(Double magnitude, String location, Long milisecondDate, String url) {

        mMagnitude = magnitude;
        mLocation = location;
        mMilisecondDate = milisecondDate;
        mUrl = url;


    }

    public Double getMagnitude() {
        return mMagnitude;
    }

    public String getLocation() {
        return mLocation;
    }

    public Long getMilisecondDate() {
        return mMilisecondDate;
    }

    public String getUrl(){return mUrl;}


}


