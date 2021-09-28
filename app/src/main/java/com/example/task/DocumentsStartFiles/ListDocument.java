package com.example.task.DocumentsStartFiles;

public class ListDocument {
    public String name;
    public String type;
    public String document_type;
    public String unique_code;
    public String expiry_date;
    public String imagecode;

    public ListDocument(String name, String type, String document_type, String unique_code, String expiry_date, String imagecode) {
        this.name = name;
        this.type = type;
        this.document_type = document_type;
        this.unique_code = unique_code;
        this.expiry_date = expiry_date;
        this.imagecode = imagecode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDocument_type() {
        return document_type;
    }

    public void setDocument_type(String document_type) {
        this.document_type = document_type;
    }

    public String getUnique_code() {
        return unique_code;
    }

    public void setUnique_code(String unique_code) {
        this.unique_code = unique_code;
    }

    public String getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(String expiry_date) {
        this.expiry_date = expiry_date;
    }

    public String getImagecode() {
        return imagecode;
    }

    public void setImagecode(String imagecode) {
        this.imagecode = imagecode;
    }
}
