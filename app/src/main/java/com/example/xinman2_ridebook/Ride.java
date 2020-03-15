package com.example.xinman2_ridebook;

import android.os.Parcel;
import android.os.Parcelable;

/*
    Ride class
    - Main purpose: maintain the details for each ride
    - It is designed to be a parcelable object so that it
        can be easily sent using intent between activities
 */


public class Ride implements Parcelable{

    private String date; // format: yyyy-mm-dd

    private String time; // format: hh:mm

    private double distance; // non-negative in km

    private double avgSpeed; // non-negative in km/h

    private int avgCadence; // non-negative

    private String comment; // up to 20 chars

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
        dest.writeString(time);
        dest.writeDouble(distance);
        dest.writeDouble(avgSpeed);
        dest.writeInt(avgCadence);
        dest.writeString(comment);
    }

    public static final Parcelable.Creator<Ride> CREATOR=new Parcelable.Creator<Ride>() {

        @Override
        public Ride createFromParcel(Parcel source) {
            Ride ride = new Ride();
            ride.date = source.readString();
            ride.time = source.readString();
            ride.distance = source.readDouble();
            ride.avgSpeed = source.readDouble();
            ride.avgCadence = source.readInt();
            ride.comment = source.readString();
            return ride;
        }

        @Override
        public Ride[] newArray(int size) {
            return new Ride[size];
        }

    };

    // constructors

    // with no arguments
    public Ride() {
        this.date = "";
        this.time = "";
        this.distance = 0;
        this.avgSpeed = 0;
        this.avgCadence = 0;
        this.comment = "";
    }

    // comment field is left blank for a ride
    public Ride(String date, String time, double distance, double avg_speed, int avg_cadence) {
        this.date = date;
        this.time = time;
        this.distance = distance;
        this.avgSpeed = avg_speed;
        this.avgCadence = avg_cadence;
        this.comment = "";
    }
    // all information is given
    public Ride(String date, String time, double distance, double avg_speed, int avg_cadence, String comment) {
        this.date = date;
        this.time = time;
        this.distance = distance;
        this.avgSpeed = avg_speed;
        this.avgCadence = avg_cadence;
        this.comment = comment;
    }


    // Getters and Setters

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance (double distance) {
        this.distance = distance;
    }

    public double getAvgSpeed() {
        return avgSpeed;
    }

    public void setAvgSpeed(double avgSpeed) {
        this.avgSpeed = avgSpeed;
    }

    public int getAvgCadence() {
        return avgCadence;
    }

    public void setAvgCadence(int avgCadence) {
        this.avgCadence = avgCadence;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        if (comment.length() <= 20) {
            this.comment = comment;
        } else {
            throw new IllegalArgumentException("Comment is up to 20 characters!");
        }

    }

    }
