package com.example.task.CancelRideFiles;

public class CancelRideRequest {
    public String ride_id;
    public String client_id;
    public String driver_id;
    public String reason;

    public String getRide_id() {
        return ride_id;
    }

    public void setRide_id(String ride_id) {
        this.ride_id = ride_id;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(String driver_id) {
        this.driver_id = driver_id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
