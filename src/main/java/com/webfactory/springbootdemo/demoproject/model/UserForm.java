package com.webfactory.springbootdemo.demoproject.model;


import java.util.List;
import java.util.Set;

public class UserForm {

    String firstName;
    String lastName;
    String nickname;
    String password;
    String email;
    String username;
    Long id;

    Location location;

    List<Role> roles;

    public UserForm() {

    }

    public UserForm(Long id, String firstName, String lastName, String nickname, String username, String password, String email, Location location, List<Role> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.password = password;
        this.username = username;
        this.email = email;
        this.id = id;
        this.location = location;
        this.roles = roles;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Location getLocation() {
        return this.location;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
