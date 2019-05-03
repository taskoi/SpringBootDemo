package com.webfactory.springbootdemo.demoproject.service;

import com.webfactory.springbootdemo.demoproject.model.User;
import com.webfactory.springbootdemo.demoproject.persistance.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User createUser(User user){

        User userNew = new User();
        userNew.setEmail(user.getEmail());
        userNew.setFirstName(user.getFirstName());
        userNew.setLastName(user.getLastName());
        userNew.setNickname(user.getNickname());
        userNew.setPassword(user.getPassword());

        return userRepository.save(userNew);
    }
}
