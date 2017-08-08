package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Benjamin on 8/8/2017.
 * * An ArrayAdapter used to populate the ListView
 * {@link EarthquakeArrayAdapter} is an {@link ArrayAdapter} that can provide the layout for each list item
 * based on a data source, which is a list of {@link Earthquake} objects.
 */

public class EarthquakeArrayAdapter extends ArrayAdapter<Earthquake> {


    /**
     * create a new {@link EarthquakeArrayAdapter} object
     *
     * @param context     is the current context (activity) that is been used to create the {@link ArrayAdapter}
     * @param earthquakes is the list of {@link Earthquake}s to be displayed
     */
    public EarthquakeArrayAdapter(Context context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        // Get the {@link Earthquake} object located at this position in the list
        Earthquake currentEarthquake = getItem(position);

        // find the current magnitude of the currentEarthquake from the list_item.xml view
        TextView tv_magnitude = (TextView)listItemView.findViewById(R.id.textView_magnitude);
        // get the earthquake magnitude for the place
        // set the magnitude on the magnitude textView
        tv_magnitude.setText(String.valueOf(currentEarthquake.getMagnitude()));

        // find the name of the currentEarthquake from the list_item.xml layout
        TextView tv_name = (TextView)listItemView.findViewById(R.id.textView_place_name);
        // get the name of the place the earthquake occurred and set it on the name textview
        tv_name.setText(currentEarthquake.getName());

        // find the time of the currentEarthquake from the list_item.xml layout
        TextView tv_time = (TextView)listItemView.findViewById(R.id.textView_time_of_occurrence);
        // get the time of occurence of the earthquake and set it on the appropriate textView
        tv_time.setText(String.valueOf(currentEarthquake.getTime()));

        return listItemView;
    }
}
