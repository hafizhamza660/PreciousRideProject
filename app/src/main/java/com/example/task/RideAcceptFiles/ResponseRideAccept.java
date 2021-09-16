package com.example.task.RideAcceptFiles;


public class ResponseRideAccept {

  public Data data;
  public String message;
  public String chat_id;

  public String getChat_id() {
    return chat_id;
  }

  public void setChat_id(String chat_id) {
    this.chat_id = chat_id;
  }

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
