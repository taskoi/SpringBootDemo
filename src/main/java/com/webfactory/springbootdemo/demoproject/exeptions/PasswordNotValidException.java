package com.webfactory.springbootdemo.demoproject.exeptions;

public class PasswordNotValidException extends Exception {
    public PasswordNotValidException(String s){
        super(s);
    }
}
