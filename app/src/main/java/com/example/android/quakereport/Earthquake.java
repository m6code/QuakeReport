package com.example.android.quakereport;

/**
 * Created by Benjamin on 8/8/2017.
 * A class that holds the relevant information for an earthquake
 */

public class Earthquake {

    private String mName;
    private double mMagnitude;
    private long mTime;
    private String mURL;

    /**
     * Creates a constructor to hold earthquake information
     *
     * @param magnitude the magnitude of the earthquake
     * @param name      the name of the place the earthquake occurred
     * @param time      the time when the earthquake occurred
     */
    public Earthquake(double magnitude, String name, long time, String url) {
        mMagnitude = magnitude;
        mName = name;
        mTime = time;
        mURL = url;
    }

    /**
     * Get the Name
     *
     * @return name of the place
     */
    public String getName() {
        return mName;
    }

    /**
     * Get the magnitude
     *
     * @return the magnitude of the earthquake
     */
    public double getMagnitude() {
        return mMagnitude;
    }

    /**
     * Get the time
     *
     * @return the time the earthquake occurred
     */
    public long getTime() {
        return mTime;
    }

    public String getURL(){
        return mURL;
    }

}
