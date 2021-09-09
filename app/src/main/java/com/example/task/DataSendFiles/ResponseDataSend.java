package com.example.task.DataSendFiles;


import java.util.HashMap;
import java.util.List;

public class ResponseDataSend {
      public Total_Distance total_distance;
      public Duration duration;
    public String start_address;
    public String end_address;
    public List<Steps> steps;


    public Total_Distance getTotal_distance() {
        return total_distance;
    }

    public void setTotal_distance(Total_Distance total_distance) {
        this.total_distance = total_distance;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public String getStart_address() {
        return start_address;
    }

    public void setStart_address(String start_address) {
        this.start_address = start_address;
    }

    public String getEnd_address() {
        return end_address;
    }

    public void setEnd_address(String end_address) {
        this.end_address = end_address;
    }

    public List<Steps> getSteps() {
        return steps;
    }

    public void setSteps(List<Steps> steps) {
        this.steps = steps;
    }
}
