package com.webfactory.springbootdemo.demoproject.model.reguest.bodies;

import com.webfactory.springbootdemo.demoproject.model.Location;
import com.webfactory.springbootdemo.demoproject.model.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PostForm {

    private Long id;

    @NotNull(message = "Post title cannot be null")
    @Size(max = 120, message = "Maximum title characters are 120")
    private String title;

    @NotNull(message = "Post description cannot be null")
    @Size(max = 1000, message = "Maximum description characters are 1000")
    private String description;

    @NotNull(message = "Post must to be linked to a user")
    private User user;

    @NotNull(message = "Post must to be linked to a location")
    private Location location;

    public PostForm() {
    }

    public PostForm(Long id, String title, String description, User user, Location location) {
        this.title = title;
        this.description = description;
        this.id = id;
        this.user = user;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public User getUser() {
        return this.user;
    }

    public Location getLocation() {
        return this.location;
    }
}
