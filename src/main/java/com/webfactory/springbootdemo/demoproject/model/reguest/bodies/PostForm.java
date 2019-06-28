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
    private Long userId;

    @NotNull(message = "Post must to be linked to a location")
    private Location location;

    public PostForm() {
    }

    public PostForm(Long id, String title, String description, Long userId, Location location) {
        this.title = title;
        this.description = description;
        this.id = id;
        this.userId = userId;
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

    public Long getUser() {
        return this.userId;
    }

    public Location getLocation() {
        return this.location;
    }
}
