package com.webfactory.springbootdemo.demoproject.exeptions;

public class LocationMissingParameterException extends Exception {
    public LocationMissingParameterException(String s){
        super(s);
    }
}
