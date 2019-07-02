package com.webfactory.springbootdemo.demoproject.model.reguest.bodies;

import com.webfactory.springbootdemo.demoproject.model.Location;
import com.webfactory.springbootdemo.demoproject.model.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

public class UserModify {
    private String username;
    @Size(max = 20, message = "First name must to be smaller than 20 characters")
    private String firstName;

    private String nickname;
    @Size(max = 20, message = "Last name must to be smaller than 20 characters")
    private String lastName;
    @Email
    @Size(max = 120, message = "Email must to be smaller than 120 characters")
    private String email;
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "Password must have minimum 1 char, 1 digit and 1 specital character")
    @Size(min = 8, max = 120, message = "Password must to be between 8 and 120 characters")
    private String password;
    private Location location;
    private List<Role> roles;

    public UserModify(){}

    public UserModify(String username,String nickname,String firstName, String lastName, String email, String password, Location location, List<Role> roles) {
        this.firstName = firstName;
        this.username = username;
        this.nickname = nickname;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.location = location;
        this.roles = roles;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public String getNickname() {
        return nickname;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Location getLocation() {
        return location;
    }

    public List<Role> getRoles() {
        return roles;
    }
}
