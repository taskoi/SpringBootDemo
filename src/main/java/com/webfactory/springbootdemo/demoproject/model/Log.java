package com.webfactory.springbootdemo.demoproject.model;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.swing.text.DateFormatter;
import java.text.SimpleDateFormat;
import java.util.Date;

@Document(collection = "log")
public class Log {

    private String type;
    private String description;
    private String date;


    public Log() {

    }

    public Log(String type, String description) {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        this.date = simpleDateFormat.format(new Date());
        this.type = type + "method called";
        this.description = "returned result of " + description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

// todo: da zacuvash data vo baza