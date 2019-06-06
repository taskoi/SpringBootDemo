package com.webfactory.springbootdemo.demoproject.exeptions;

public class UserMissingParameterException extends Exception{
    public UserMissingParameterException(String s){
        super(s);
    }
}
