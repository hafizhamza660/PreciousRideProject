package com.example.task.RideNegotiate;

public class RequestRideNegotiate {
  public String driver_id;
  public String ride_id;
  public String negotiate_price;

    public String getNegotiate_price() {
        return negotiate_price;
    }

    public void setNegotiate_price(String negotiate_price) {
        this.negotiate_price = negotiate_price;
    }

    public String getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(String driver_id) {
        this.driver_id = driver_id;
    }

    public String getRide_id() {
        return ride_id;
    }

    public void setRide_id(String ride_id) {
        this.ride_id = ride_id;
    }
}
