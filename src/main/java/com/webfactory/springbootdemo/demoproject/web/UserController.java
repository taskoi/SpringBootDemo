package com.webfactory.springbootdemo.demoproject.web;

import com.webfactory.springbootdemo.demoproject.model.User;
import com.webfactory.springbootdemo.demoproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/createUser")
    public User createUser(@Valid @RequestBody User user, HttpServletResponse response){
        User userNew = userService.createUser(user);
        response.setHeader("Location", "/users/" + user.getId());
        return userNew;
    }
}
