package com.webfactory.springbootdemo.demoproject.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "User")
public class User {

    private String email;
    private String password;
    private String nickname;
    private String firstName;
    private String lastName;

    public User(){}

    public User(String email, String password, String nickname, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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
}
