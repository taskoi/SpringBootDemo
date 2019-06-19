package com.webfactory.springbootdemo.demoproject.exeptions.user.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class UserNotFoundException extends Exception {
    private String nickname;

    public UserNotFoundException(String nickname) {
        super("User not found: " + nickname);
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }
}
