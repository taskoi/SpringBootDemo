package com.webfactory.springbootdemo.demoproject.model;

public class PostForm {

    private Long id;
    private String title;
    private String description;

    private User user;
    private Location location;

    public PostForm(){}

    public PostForm(Long id,String title, String description,User user,Location location) {
        this.title = title;
        this.description = description;
        this.id = id;
        this.user = user;
        this.location = location;
    }

    public Long getId(){
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public User getUser(){
        return this.user;
    }

    public Location getLocation(){
        return this.location;
    }
}
