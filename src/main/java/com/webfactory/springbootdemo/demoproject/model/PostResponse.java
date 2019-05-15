package com.webfactory.springbootdemo.demoproject.model;

public class PostResponse {
    private Long id;
    private String title;
    private String description;
    private Long userId;
    private String userEmail;
    private Location location;

    public PostResponse() {
    }

    public PostResponse(Long id, String title, String description, Long userId, String userEmail, Location location) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.userEmail = userEmail;
        this.userId = userId;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
