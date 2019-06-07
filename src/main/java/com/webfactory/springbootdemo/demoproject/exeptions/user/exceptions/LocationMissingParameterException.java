package com.webfactory.springbootdemo.demoproject.exeptions.user.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class LocationMissingParameterException extends Exception {
    public LocationMissingParameterException(String s){
        super(s);
    }
}
