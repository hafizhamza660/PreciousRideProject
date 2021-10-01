package com.example.task.Models;

public class Chat {

    private String sender;
    private String receiver;
    private String message;
    private String messagepostid;
    private boolean isseen;

    public Chat() {
    }

    public String getMessagepostid() {
        return messagepostid;
    }

    public void setMessagepostid(String messagepostid) {
        this.messagepostid = messagepostid;
    }

    public Chat(String sender, String receiver, String message, String messagepostid, boolean isseen) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.messagepostid = messagepostid;
        this.isseen = isseen;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isIsseen() {
        return isseen;
    }


    public void setIsseen(boolean isseen) {
        this.isseen = isseen;
    }
}
