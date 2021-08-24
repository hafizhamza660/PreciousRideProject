package com.example.task.RideNegotiate;


import com.example.task.RideAcceptFiles.Data;

public class ResponseRideNegotiate {

  public Data data;
  public String message;

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Data getData() {
    return data;
  }

  public void setData(Data data) {
    this.data = data;
  }
}
