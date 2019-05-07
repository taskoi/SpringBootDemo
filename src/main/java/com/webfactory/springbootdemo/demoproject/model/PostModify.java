package com.webfactory.springbootdemo.demoproject.model;

public class PostModify {
    private String title;
    private String description;



    public PostModify(){}

    public PostModify(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
