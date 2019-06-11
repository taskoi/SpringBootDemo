package com.webfactory.springbootdemo.demoproject.exeptions.user.exceptions;




public class NicknameNotValidException extends RuntimeException {
    public NicknameNotValidException(String s){
        super(s);
    }
}
