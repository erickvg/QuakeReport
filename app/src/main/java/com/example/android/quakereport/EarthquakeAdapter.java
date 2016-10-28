package com.example.android.quakereport;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
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

import static com.example.android.quakereport.R.id.date;
import static com.example.android.quakereport.R.id.magnitude;

/**
 * Created by Student on 03/10/2016.
 */

public class EarthquakeAdapter extends ArrayAdapter<Quake> {

    private static final String LOCATION_SPLIT = "of";

    private static final String LOG_TAG = EarthquakeAdapter.class.getSimpleName();


    public EarthquakeAdapter(Activity context, ArrayList<Quake> earthquakes) {

        super(context, 0, earthquakes);

    }

    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd,yyyy");
        return dateFormat.format(dateObject);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View earthquakeListView = convertView;

        if (earthquakeListView == null) {

            earthquakeListView = LayoutInflater.from(getContext())
                    .inflate(R.layout.earthquake_item, parent, false);
        }

        Quake currentQuake = getItem(position);


        TextView magTextView = (TextView) earthquakeListView.findViewById(magnitude);
        String formattedMagnitude = formatMagnitude(currentQuake.getMagnitude());
        magTextView.setText(formattedMagnitude);

        GradientDrawable magnitudeCircle = (GradientDrawable) magTextView.getBackground();
        int magnitudeColor = getMagnitudeColor(currentQuake.getMagnitude());
        magnitudeCircle.setColor(magnitudeColor);


        String newLoc = currentQuake.getLocation();
        String offsetLoc;
        String primaryLoc;

        if (newLoc.contains(LOCATION_SPLIT)) {
            String[] parts = newLoc.split(LOCATION_SPLIT);
            offsetLoc = parts[0] + LOCATION_SPLIT;
            primaryLoc = parts[1];
        } else {
            offsetLoc = getContext().getString(R.string.near);
            primaryLoc = newLoc;
        }

        TextView offsetLocation = (TextView) earthquakeListView.findViewById(R.id.offsetplace);
        offsetLocation.setText(offsetLoc);


        TextView primaryLocation = (TextView) earthquakeListView.findViewById(R.id.primaryplace);
        primaryLocation.setText(primaryLoc);


        Date dateObject = new Date(currentQuake.getMilisecondDate());

        TextView dateTextView = (TextView) earthquakeListView.findViewById(date);
        String formattedDate = formatDate(dateObject);
        dateTextView.setText(formattedDate);

        TextView timeView = (TextView) earthquakeListView.findViewById(R.id.time);
        String formattedTime = formatTime(dateObject);
        timeView.setText(formattedTime);



        return earthquakeListView;


    }

    private String formatMagnitude(double mag) {

        DecimalFormat magFormat = new DecimalFormat("0.0");
        return magFormat.format(mag);
    }

    private int getMagnitudeColor(double mag) {

        int magnitudeResourceID;
        int magFloor = (int) Math.floor(mag);
        switch (magFloor) {
            case 0:
            case 1:
                magnitudeResourceID = R.color.magnitude1;
                break;
            case 2:
                magnitudeResourceID = R.color.magnitude2;
                break;
            case 3:
                magnitudeResourceID = R.color.magnitude3;
                break;
            case 4:
                magnitudeResourceID = R.color.magnitude4;
                break;
            case 5:
                magnitudeResourceID = R.color.magnitude5;
                break;
            case 6:
                magnitudeResourceID = R.color.magnitude6;
                break;
            case 7:
                magnitudeResourceID = R.color.magnitude7;
                break;
            case 8:
                magnitudeResourceID = R.color.magnitude8;
                break;
            case 9:
                magnitudeResourceID = R.color.magnitude9;
                break;
            default:
                magnitudeResourceID = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(),magnitudeResourceID);
    }


}
