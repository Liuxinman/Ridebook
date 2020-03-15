package com.example.xinman2_ridebook;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

/*
    MainActivity
    - Main purpose: show the list of rides;
        delete an existing ride;
        navigate to AddRideActivity to add a ride;
        navigate to RideDetailActivity to view/edit an existing ride
 */

public class MainActivity extends AppCompatActivity {

    private RideList rideList = new RideList();
    private RideAdapter adapter;
    private TextView totalDistance;
    private int deleteRideItemIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ListView rideListView = (ListView) findViewById(R.id.ride_list_view);

        View total_distance_view = LayoutInflater.from(MainActivity.this).inflate(R.layout.total_distance_view, null);

        totalDistance = (TextView) total_distance_view.findViewById(R.id.total_distance);

        totalDistance.setText(String.format("%.2f", rideList.getTotalDistance()));

        adapter = new RideAdapter(MainActivity.this, R.layout.ride_item, rideList.getRideList());
        rideListView.addFooterView(total_distance_view, null, false);


        rideListView.setAdapter(adapter);

        rideListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                ArrayList<Ride> rideArrayList = rideList.getRideList();
                Ride ride = rideArrayList.get(position);
                Intent intent = new Intent(MainActivity.this, RideDetailActivity.class);
                intent.putExtra("detailed_ride", ride);
                intent.putExtra("detailed_ride_index", position);

                startActivityForResult(intent, 1);


            }
        });

        rideListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                deleteRideItemIndex = i;
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("Delete a Ride");
                dialog.setMessage("Delete is unrecoverable. Are you sure?");
                dialog.setCancelable(false);
                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        rideList.deleteRide(deleteRideItemIndex);
                        adapter.notifyDataSetChanged();
                        totalDistance.setText(String.format("%.2f", rideList.getTotalDistance()));
                        Toast.makeText(MainActivity.this, "Deleted a ride", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "Delete canceled", Toast.LENGTH_SHORT).show();
                    }
                });

                dialog.show();
                return true;
            }
        });
        


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_ride_menu:
                Intent intent = new Intent(MainActivity.this, AddRideActivity.class);
                startActivityForResult(intent, 2);
                break;
            default:
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    Ride returnedRide = data.getParcelableExtra("ride_return");
                    int index = data.getIntExtra("ride_return_index", 0);
                    rideList.addRideWithIndex(index, returnedRide);
                    adapter.notifyDataSetChanged();
                    totalDistance.setText(String.format("%.2f", rideList.getTotalDistance()));

                } else if (resultCode == 2) {
                    int index = data.getIntExtra("ride_return_index", 0);
                    rideList.deleteRide(index);
                    adapter.notifyDataSetChanged();
                    totalDistance.setText(String.format("%.2f", rideList.getTotalDistance()));
                }
                break;
            case 2:
                if(resultCode == RESULT_OK) {
                    Ride addedRide = data.getParcelableExtra("ride_add");
                    rideList.addRide(addedRide);
                    adapter.notifyDataSetChanged();
                    totalDistance.setText(String.format("%.2f", rideList.getTotalDistance()));
                }
                break;
            default:

        }
    }


}
