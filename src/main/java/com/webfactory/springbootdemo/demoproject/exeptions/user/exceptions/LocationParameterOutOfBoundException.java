package com.webfactory.springbootdemo.demoproject.exeptions.user.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class LocationParameterOutOfBoundException extends Exception {
    public LocationParameterOutOfBoundException(String s){
        super(s);
    }
}
