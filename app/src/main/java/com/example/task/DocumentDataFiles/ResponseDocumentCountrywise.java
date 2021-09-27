package com.example.task.DocumentDataFiles;


import java.util.List;

public class ResponseDocumentCountrywise {
    public String message;
    public List<Data> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
}
