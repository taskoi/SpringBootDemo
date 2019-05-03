package com.webfactory.springbootdemo.demoproject.exeptions;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String s){
        super(s);
    }
}
