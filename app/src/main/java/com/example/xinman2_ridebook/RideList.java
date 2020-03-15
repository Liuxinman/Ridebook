package com.example.xinman2_ridebook;

import java.util.ArrayList;

/*
    RideList class
    - Main purpose: keep track of all existing rides and total distance
    - Design Rationale: can easily update total distance when adding, editing
        and deleting rides from the list
 */

public class RideList {

    private ArrayList<Ride> rideList;

    private double totalDistance;

    public RideList() {
        this.rideList = new ArrayList<>();
        this.totalDistance = 0;
    }

    public void addRide(Ride ride) {
        rideList.add(ride);
        totalDistance += ride.getDistance();
    }

    public void addRideWithIndex(int i, Ride ride) {
        this.totalDistance -= rideList.get(i).getDistance();
        rideList.set(i, ride);
        this.totalDistance += ride.getDistance();
    }

    public void deleteRide(int index) {
        Ride ride = rideList.get(index);
        totalDistance -= ride.getDistance();
        rideList.remove(index);
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    public ArrayList<Ride> getRideList() {
        return rideList;
    }


}
