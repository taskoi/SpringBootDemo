package com.webfactory.springbootdemo.demoproject.exeptions;

public class PostMissingParameterException extends Exception {
    public PostMissingParameterException(String s){
        super(s);
    }
}
