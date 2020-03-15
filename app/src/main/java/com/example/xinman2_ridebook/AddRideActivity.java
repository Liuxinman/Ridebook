package com.example.xinman2_ridebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/*
    AddRideActivity
    - Main purpose: add a ride
    - when user adds an existing ride, make sure no fields are empty except comment
        and use FormatValidationTool to make sure all fields are in proper format.
 */

public class AddRideActivity extends AppCompatActivity {

    private EditText addDateEdit;

    private EditText addTimeEdit;

    private EditText addDistanceEdit;

    private EditText addAvgSpeedEdit;

    private EditText addAvgCadenceEdit;

    private EditText addCommentEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ride);

        addDateEdit = (EditText) findViewById(R.id.add_date_detail);
        addTimeEdit = (EditText) findViewById(R.id.add_time_detail);
        addDistanceEdit = (EditText) findViewById(R.id.add_distance_detail);
        addAvgSpeedEdit = (EditText) findViewById(R.id.add_avg_speed_detail);
        addAvgCadenceEdit = (EditText) findViewById(R.id.add_avg_cadence_detail);
        addCommentEdit = (EditText) findViewById(R.id.add_comment_detail);

        Button addRide = (Button) findViewById(R.id.add_ride_button);
        addRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(addDateEdit.getText()) || TextUtils.isEmpty(addTimeEdit.getText()) || TextUtils.isEmpty(addDistanceEdit.getText()) || TextUtils.isEmpty(addAvgSpeedEdit.getText()) || TextUtils.isEmpty(addAvgCadenceEdit.getText())) {
                    Toast.makeText(AddRideActivity.this, "Please fill all fields marked by *", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!FormatValidationTool.dateValidator(addDateEdit.getText().toString())) {
                    Toast.makeText(AddRideActivity.this, "Date is either invalid or in invalid format", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!FormatValidationTool.timeValidator(addTimeEdit.getText().toString())) {
                    Toast.makeText(AddRideActivity.this, "Time is either invalid or in invalid format", Toast.LENGTH_SHORT).show();
                    return;
                }


                Ride ride = new Ride(
                        addDateEdit.getText().toString(),
                        addTimeEdit.getText().toString(),
                        Double.valueOf(addDistanceEdit.getText().toString()),
                        Double.valueOf(addAvgSpeedEdit.getText().toString()),
                        Integer.valueOf(addAvgCadenceEdit.getText().toString()),
                        addCommentEdit.getText().toString());


                Intent intent = new Intent();
                intent.putExtra("ride_add", ride);
                setResult(RESULT_OK, intent);
                Toast.makeText(AddRideActivity.this, "Added a ride", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        Button cancelAdd = (Button) findViewById(R.id.cancel_add_ride_button);
        cancelAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AddRideActivity.this, "Add canceled", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }


}
