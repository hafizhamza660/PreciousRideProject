package com.example.task.DocumentUploadFiles;

import java.util.HashMap;

public class RequestDocument {
  public String driver_id;
  public String front_image;
  public String back_image;
  public String type;
  public String card_number;
  public String expiry;

  public String getDriver_id() {
    return driver_id;
  }

  public void setDriver_id(String driver_id) {
    this.driver_id = driver_id;
  }

  public String getFront_image() {
    return front_image;
  }

  public void setFront_image(String front_image) {
    this.front_image = front_image;
  }

  public String getBack_image() {
    return back_image;
  }

  public void setBack_image(String back_image) {
    this.back_image = back_image;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getCard_number() {
    return card_number;
  }

  public void setCard_number(String card_number) {
    this.card_number = card_number;
  }

  public String getExpiry() {
    return expiry;
  }

  public void setExpiry(String expiry) {
    this.expiry = expiry;
  }



}
