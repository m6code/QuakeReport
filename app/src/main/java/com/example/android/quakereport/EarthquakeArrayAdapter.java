package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Benjamin on 8/8/2017.
 * * An ArrayAdapter used to populate the ListView
 * {@link EarthquakeArrayAdapter} is an {@link ArrayAdapter} that can provide the layout for each list item
 * based on a data source, which is a list of {@link Earthquake} objects.
 */

public class EarthquakeArrayAdapter extends ArrayAdapter<Earthquake> {

    private static final String LOCATION_SEPERATOR = "of";
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
        TextView tv_magnitude = (TextView) listItemView.findViewById(R.id.textView_magnitude);
        // format the magnitude to 1 decimal place
        String formattedMagnitude = formatMagnitude(currentEarthquake.getMagnitude());
        // get the earthquake magnitude for the place
        // set the magnitude on the magnitude textView
        tv_magnitude.setText(formattedMagnitude);

        // Get the location/ place as a string
        String fullLocationName = currentEarthquake.getName();
        String locationOffset;
        String locationPrimary;

        // Checks if the location name contains the word "of" or not
        // and split the location text where of appears
        if (fullLocationName.contains(LOCATION_SEPERATOR)) {
            // Splits the location into two from where the of appears
            String[] parts = fullLocationName.split(LOCATION_SEPERATOR);
            locationOffset = parts[0] + LOCATION_SEPERATOR;
            locationPrimary = parts[1];

        } else {
            // set the location offset to "Near the" where the location does not contain of
            // and get the name of that location
            locationOffset = "Near the";
            locationPrimary = currentEarthquake.getName();
        }

        // find the name of the location offset of the currentEarthquake from the list_item.xml layout
        TextView tv_locationPrimary = (TextView) listItemView.findViewById(R.id.textView_place_offset);
        // set the text on the view
        tv_locationPrimary.setText(locationOffset);

        // find the name of the primary location of the currentEarthquake from the list_item.xml layout
        TextView tv_locationOffset = (TextView) listItemView.findViewById(R.id.textView_place_location);
        // set the text on the view
        tv_locationOffset.setText(locationPrimary);

        // Create a new date object from the time in milliseconds
        Date dateObject = new Date(currentEarthquake.getTime());

        // find the time of the currentEarthquake from the list_item.xml layout
        TextView tv_time = (TextView) listItemView.findViewById(R.id.textView_time_of_occurrence);
        // Format the time string (i.e "5:00PM")
        String formattedTime = formatTime(dateObject);
        // get the time of occurence of the earthquake and set it on the appropriate textView
        tv_time.setText(formattedTime);

        // find the textview with id date
        TextView tv_date = (TextView) listItemView.findViewById(R.id.date);
        // format the date string (i.e "Mar 3, 2009")
        String formattedDate = formatDate(dateObject);
        tv_date.setText(formattedDate);

        // Set the proper background color on the magnitude circle
        // Fetch the background from the TextView, which is a GradientDrawable
        GradientDrawable magnitudeCircle = (GradientDrawable)tv_magnitude.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        // Return the list item view
        return listItemView;
    }

    /**
     * Return the formatted date string (i.e. Mar 3, 1990) from a date object
     *
     * @param dateObject is the date object
     * @return the formatted date
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e "4:00 AM") from a Date object.
     *
     * @param dateObject is the Date object
     * @return the formatted time
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    /**
     * Return formatted magnitude string in 1 decimal place (i.e 6.7)
     * from decimal magnitude value
     * @param magnitude is the magnitude in double
     * @return the formatted magnitude in 1 decimal place
     */
    private String formatMagnitude(double magnitude){
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

    private int getMagnitudeColor(double magnitude){
        int magnitudeColorResourceId;
        int magnitudeFloor = (int)Math.floor(magnitude); // rounds the magnitude from double to integer
        // check the magnitude of the earthquake and sets the
        // appropriate color background for the magnitude_circle.xml
        switch (magnitudeFloor){
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId =R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId =R.color.magnitude10plus;
                break;
        }

        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}
