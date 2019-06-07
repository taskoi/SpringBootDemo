package com.webfactory.springbootdemo.demoproject.exeptions.post.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class PostParameterOutOfBoundException extends Exception {
    public PostParameterOutOfBoundException(String s){
        super(s);
    }
}
