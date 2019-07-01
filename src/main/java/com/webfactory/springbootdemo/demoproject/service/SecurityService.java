package com.webfactory.springbootdemo.demoproject.service;

public interface SecurityService {

     boolean hasAccessPost(String username,int postId);

     boolean hasAccessUser(String username,Long userId);
}
