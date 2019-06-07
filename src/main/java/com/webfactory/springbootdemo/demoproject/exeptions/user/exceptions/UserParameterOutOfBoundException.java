package com.webfactory.springbootdemo.demoproject.exeptions.user.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class UserParameterOutOfBoundException extends Exception {
    public UserParameterOutOfBoundException(String s){
        super(s);
    }
}
