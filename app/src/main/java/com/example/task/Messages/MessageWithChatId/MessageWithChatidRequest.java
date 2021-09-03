package com.example.task.Messages.MessageWithChatId;

public class MessageWithChatidRequest {
  public String driver_id;
  public String client_id;
  public String type;
  public String text;
  public String chat_id;

  public String getDriver_id() {
    return driver_id;
  }

  public void setDriver_id(String driver_id) {
    this.driver_id = driver_id;
  }

  public String getClient_id() {
    return client_id;
  }

  public void setClient_id(String client_id) {
    this.client_id = client_id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getChat_id() {
    return chat_id;
  }

  public void setChat_id(String chat_id) {
    this.chat_id = chat_id;
  }
}
