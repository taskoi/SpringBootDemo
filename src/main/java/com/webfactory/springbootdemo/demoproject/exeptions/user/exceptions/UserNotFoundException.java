package com.webfactory.springbootdemo.demoproject.exeptions.user.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class UserNotFoundException extends Exception {
    public UserNotFoundException(String s){
        super(s);
    }
}
