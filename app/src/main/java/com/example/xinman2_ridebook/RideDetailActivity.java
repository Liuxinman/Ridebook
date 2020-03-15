package com.example.xinman2_ridebook;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

/*
    RideDetailActivity
    - Main purpose: view / edit the details of an existing ride
    - another place to delete an existing ride
        If the user want to see the details of an existing ride to
        make sure he wants to delete it, he can delete from here.
    - when user edits an existing ride, make sure no fields are empty except comment
        and use FormatValidationTool to make sure all fields are in proper format.
 */


public class RideDetailActivity extends AppCompatActivity {

    private Ride ride;

    private int position;

    private TextView dateText;

    private TextView timeText;

    private TextView distanceText;

    private TextView avgSpeedText;

    private TextView avgCadenceText;

    private TextView commentText;

    private EditText dateEdit;

    private EditText timeEdit;

    private EditText distanceEdit;

    private EditText avgSpeedEdit;

    private EditText avgCadenceEdit;

    private EditText commentEdit;

    private ViewSwitcher editRideSwitcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_detail);



        Intent intent = getIntent();
        ride = (Ride) intent.getParcelableExtra("detailed_ride");
        position = intent.getIntExtra("detailed_ride_index", 0);


        dateText = (TextView) findViewById(R.id.date_detail);
        timeText = (TextView) findViewById(R.id.time_detail);
        distanceText = (TextView) findViewById(R.id.distance_detail);
        avgSpeedText = (TextView) findViewById(R.id.avg_speed_detail);
        avgCadenceText = (TextView) findViewById(R.id.avg_cadence_detail);
        commentText = (TextView) findViewById(R.id.comment_detail);


        dateText.setText(ride.getDate());
        timeText.setText(ride.getTime());
        distanceText.setText(String.format("%.2f", ride.getDistance()));
        avgSpeedText.setText(String.format("%.2f",ride.getAvgSpeed()));
        avgCadenceText.setText(String.format("%d", ride.getAvgCadence()));
        commentText.setText(ride.getComment());

        dateEdit = (EditText) findViewById(R.id.edit_date_detail);
        timeEdit = (EditText) findViewById(R.id.edit_time_detail);
        distanceEdit = (EditText) findViewById(R.id.edit_distance_detail);
        avgSpeedEdit = (EditText) findViewById(R.id.edit_avg_speed_detail);
        avgCadenceEdit = (EditText) findViewById(R.id.edit_avg_cadence_detail);
        commentEdit = (EditText) findViewById(R.id.edit_comment_detail);

        editRideSwitcher = (ViewSwitcher) findViewById(R.id.edit_ride_switcher);


        Button editRide = (Button) findViewById(R.id.edit_button);
        editRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dateEdit.setText(dateText.getText());

                timeEdit.setText(timeText.getText());

                distanceEdit.setText(distanceText.getText());

                avgSpeedEdit.setText(avgSpeedText.getText());

                avgCadenceEdit.setText(avgCadenceText.getText());

                commentEdit.setText(commentText.getText());

                editRideSwitcher.showNext();

            }
        });

        Button deleteRide = (Button) findViewById(R.id.delete_button);
        deleteRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(RideDetailActivity.this);
                dialog.setTitle("Delete a Ride");
                dialog.setMessage("Delete is unrecoverable. Are you sure?");
                dialog.setCancelable(false);
                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent();
                        intent.putExtra("ride_return_index", position);
                        setResult(2, intent);
                        Toast.makeText(RideDetailActivity.this, "Deleted a ride", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
                dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(RideDetailActivity.this, "Delete canceled", Toast.LENGTH_SHORT).show();
                    }
                });

                dialog.show();

            }
        });

        Button finishEdit = (Button) findViewById(R.id.finish_button);
        finishEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(dateEdit.getText()) || TextUtils.isEmpty(timeEdit.getText()) || TextUtils.isEmpty(distanceEdit.getText()) || TextUtils.isEmpty(avgSpeedEdit.getText()) || TextUtils.isEmpty(avgCadenceEdit.getText())) {
                    Toast.makeText(RideDetailActivity.this, "Please fill all fields marked by *", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!FormatValidationTool.dateValidator(dateEdit.getText().toString())) {
                    Toast.makeText(RideDetailActivity.this, "Date is either invalid or in invalid format", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!FormatValidationTool.timeValidator(timeEdit.getText().toString())) {
                    Toast.makeText(RideDetailActivity.this, "Time is either invalid or in invalid format", Toast.LENGTH_SHORT).show();
                    return;
                }


                dateText.setText(dateEdit.getText());

                timeText.setText(timeEdit.getText());

                distanceText.setText(FormatValidationTool.doubleValidator(distanceEdit.getText().toString()));

                avgSpeedText.setText(FormatValidationTool.doubleValidator(avgSpeedEdit.getText().toString()));

                avgCadenceText.setText(avgCadenceEdit.getText());

                commentText.setText(commentEdit.getText());


                ride.setDate(dateText.getText().toString());
                ride.setTime(timeText.getText().toString());
                ride.setDistance(Double.valueOf(distanceText.getText().toString()));
                ride.setAvgSpeed(Double.valueOf(avgSpeedText.getText().toString()));
                ride.setAvgCadence(Integer.valueOf(avgCadenceText.getText().toString()));
                ride.setComment(commentText.getText().toString());

                editRideSwitcher.showPrevious();
            }
        });

        Button cancelEdit = (Button) findViewById(R.id.cancel_edit_ride_button);
        cancelEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editRideSwitcher.showPrevious();
                Toast.makeText(RideDetailActivity.this, "Edit canceled", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("ride_return", ride);
        intent.putExtra("ride_return_index", position);
        setResult(RESULT_OK, intent);
        finish();

    }


}
