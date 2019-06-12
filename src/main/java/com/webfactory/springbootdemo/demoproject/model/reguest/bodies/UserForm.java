package com.webfactory.springbootdemo.demoproject.model.reguest.bodies;


import com.webfactory.springbootdemo.demoproject.model.Location;
import com.webfactory.springbootdemo.demoproject.model.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

public class UserForm {

    @NotNull(message = "First name cannot be null")
    @Size(max = 20,message = "First name must to be smaller than 20 characters")
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    @Size(max = 20,message = "Last name must to be smaller than 20 characters")
    private String lastName;

    @NotNull(message = "Nickname cannot be null")
    private String nickname;

    @NotNull(message = "Password cannot be null")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "Password must have minimum 1 char, 1 digit and 1 specital character")
    @Size(min = 8,max = 120,message = "Password must to be between 8 and 120 characters")
    private String password;

    @Email
    @Size(max = 120, message = "Email must to be smaller than 120 characters")
    @NotNull(message = "Email cannot be null")
    private String email;

    @NotNull(message = "Username cannot be null")
    private String username;

    private Long id;

    private Location location;

    private List<Role> roles;

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
