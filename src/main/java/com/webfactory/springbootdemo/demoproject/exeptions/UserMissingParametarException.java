package com.webfactory.springbootdemo.demoproject.exeptions;

public class UserMissingParametarException extends Exception{
    public UserMissingParametarException(String s){
        super(s);
    }
}
