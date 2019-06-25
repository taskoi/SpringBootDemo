package com.webfactory.springbootdemo.demoproject.exeptions.post.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class PostNotFoundException extends Exception {
    public PostNotFoundException(String s) {
        super(s);
    }
}
