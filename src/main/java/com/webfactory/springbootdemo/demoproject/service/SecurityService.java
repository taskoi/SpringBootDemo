package com.webfactory.springbootdemo.demoproject.service;

import org.springframework.security.core.Authentication;

public interface SecurityService {

     boolean hasAccessPost(Authentication authentication, int postId);

     boolean hasAccessUser(Authentication authentication, Long userId);
}
