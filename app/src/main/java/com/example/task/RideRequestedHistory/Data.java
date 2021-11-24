package com.example.task.RideRequestedHistory;

public class Data {
    public String id;
    public String start_lat;
    public String start_long;
    public String end_lat;
    public String end_long;
    public String price;
    public String client_price;
    public String negotiated_price;
    public String distance;
    public String status;
    public String client_id;
    public String driver_id;
    public String created_at;
    public String updated_at;

    public String getClient_price() {
        return client_price;
    }

    public void setClient_price(String client_price) {
        this.client_price = client_price;
    }

    public String getNegotiated_price() {
        return negotiated_price;
    }

    public void setNegotiated_price(String negotiated_price) {
        this.negotiated_price = negotiated_price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStart_lat() {
        return start_lat;
    }

    public void setStart_lat(String start_lat) {
        this.start_lat = start_lat;
    }

    public String getStart_long() {
        return start_long;
    }

    public void setStart_long(String start_long) {
        this.start_long = start_long;
    }

    public String getEnd_lat() {
        return end_lat;
    }

    public void setEnd_lat(String end_lat) {
        this.end_lat = end_lat;
    }

    public String getEnd_long() {
        return end_long;
    }

    public void setEnd_long(String end_long) {
        this.end_long = end_long;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
