package com.example.task.RideRequestedHistory;



import java.util.List;

public class RideRequestHistoryResponse {
    public List<Data> data;
    public String status;

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
}
