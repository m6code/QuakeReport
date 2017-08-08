package com.example.android.quakereport;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by Benjamin on 8/8/2017.
 * * An ArrayAdapter used to populate the ListView
 * {@link EarthquakeArrayAdapter} is an {@link ArrayAdapter} that can provide the layout for each list item
 * based on a data source, which is a list of {@link Earthquake} objects.
 */

public class EarthquakeArrayAdapter extends ArrayAdapter {


    public EarthquakeArrayAdapter(Context context, ArrayList<Earthquake> earthquakes){
        super(context, 0, earthquakes);
    }
}
