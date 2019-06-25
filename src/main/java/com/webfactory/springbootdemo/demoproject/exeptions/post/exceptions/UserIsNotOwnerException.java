package com.webfactory.springbootdemo.demoproject.exeptions.post.exceptions;

public class UserIsNotOwnerException extends Exception {
    public UserIsNotOwnerException(Long id) {
        super("You are now the owner of the post with id " + id);
    }
}
