package com.webfactory.springbootdemo.demoproject.exeptions;

public class EmailNotValidException extends Exception {
    public EmailNotValidException(String s){
        super(s);
    }
}
