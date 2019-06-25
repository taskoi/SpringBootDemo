package com.webfactory.springbootdemo.demoproject.exeptions.user.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class EmailNotValidException extends Exception {
    public EmailNotValidException(String s) {
        super(s);
    }
}
