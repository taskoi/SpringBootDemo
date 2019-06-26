package com.webfactory.springbootdemo.demoproject.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.json.JSONObject;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.swing.text.DateFormatter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Document(collection = "show da")
public class Log {

    private String type;
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.OBJECT, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;

    public Log() {

    }

    public Log(String type, String description) {
        this.type = type + "method called";
        this.description = "returned result of " + description;
        this.date = new Date();
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}