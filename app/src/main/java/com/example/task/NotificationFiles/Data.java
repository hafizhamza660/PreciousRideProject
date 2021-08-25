package com.example.task.NotificationFiles;

public class Data {
 public String id;
 public String sender_type;
 public String sender_id;
 public String reciever_id;
 public String text;
 public String status;
 public String created_at;
 public String updated_at;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getSender_type() {
    return sender_type;
  }

  public void setSender_type(String sender_type) {
    this.sender_type = sender_type;
  }

  public String getSender_id() {
    return sender_id;
  }

  public void setSender_id(String sender_id) {
    this.sender_id = sender_id;
  }

  public String getReciever_id() {
    return reciever_id;
  }

  public void setReciever_id(String reciever_id) {
    this.reciever_id = reciever_id;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
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
