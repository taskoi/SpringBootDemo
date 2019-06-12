package com.webfactory.springbootdemo.demoproject.exeptions.user.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class UserExistsException extends Exception {
    public UserExistsException(String s){
        super("Email already exists!: " + s);
    }
}
