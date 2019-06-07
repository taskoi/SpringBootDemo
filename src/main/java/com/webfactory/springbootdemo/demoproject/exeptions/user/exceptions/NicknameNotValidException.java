package com.webfactory.springbootdemo.demoproject.exeptions.user.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class NicknameNotValidException extends Exception {
    public NicknameNotValidException(String s){
        super(s);
    }
}
