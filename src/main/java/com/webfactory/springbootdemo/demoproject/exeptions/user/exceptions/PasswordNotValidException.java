package com.webfactory.springbootdemo.demoproject.exeptions.user.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class PasswordNotValidException extends Exception {
    public PasswordNotValidException(String s){
        super(s);
    }
}
