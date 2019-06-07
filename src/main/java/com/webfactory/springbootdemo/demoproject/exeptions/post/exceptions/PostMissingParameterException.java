package com.webfactory.springbootdemo.demoproject.exeptions.post.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class PostMissingParameterException extends Exception {
    public PostMissingParameterException(String s){
        super(s);
    }
}
