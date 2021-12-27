package com.example.task.Models;

public class LocationModel {
    String driver_lat;
    String driver_lng;
    float locationbearing;

    public LocationModel(String driver_lat, String driver_lng,float locationbearing) {
        this.driver_lat = driver_lat;
        this.driver_lng = driver_lng;
        this.locationbearing = locationbearing;
    }

    public float getLocationbearing() {
        return locationbearing;
    }

    public void setLocationbearing(float locationbearing) {
        this.locationbearing = locationbearing;
    }

    public String getDriver_lat() {
        return driver_lat;
    }

    public void setDriver_lat(String driver_lat) {
        this.driver_lat = driver_lat;
    }

    public String getDriver_lng() {
        return driver_lng;
    }

    public void setDriver_lng(String driver_lng) {
        this.driver_lng = driver_lng;
    }
}
