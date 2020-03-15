package com.example.xinman2_ridebook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/*
    RideAdapter
    - Main purpose: connection between ListView and rideList
        set ride information for each item of the ListView
 */

public class RideAdapter extends ArrayAdapter<Ride> {

    private int resourceId;

    public RideAdapter(Context context, int textViewResourceId, ArrayList<Ride> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Ride ride = getItem(position);

        View view;

        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        } else {
            view = convertView;
        }


        TextView date_item = (TextView) view.findViewById(R.id.date);
        TextView time_item = (TextView) view.findViewById(R.id.time);
        TextView distance_item = (TextView) view.findViewById(R.id.distance);

        date_item.setText(ride.getDate());
        time_item.setText(ride.getTime());
        distance_item.setText(Double.toString(ride.getDistance()));

        return view;
    }
}
