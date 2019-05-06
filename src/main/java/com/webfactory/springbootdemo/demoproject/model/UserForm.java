package com.webfactory.springbootdemo.demoproject.model;


public class UserForm {

    String firstName;
    String lastName;
    String nickname;
    String password;
    String email;
    Long id;

    Location location;

    public UserForm(){

    }

    public UserForm(Long id,String firstName, String lastName, String nickname, String password, String email, Location location) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.id = id;
        this.location = location;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id){
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

    public Location getLocation(){
        return this.location;
    }
}
