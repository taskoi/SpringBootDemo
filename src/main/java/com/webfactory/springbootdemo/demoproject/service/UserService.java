package com.webfactory.springbootdemo.demoproject.service;

import com.webfactory.springbootdemo.demoproject.persistance.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


}
