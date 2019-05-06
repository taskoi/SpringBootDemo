package com.webfactory.springbootdemo.demoproject.web;

import com.webfactory.springbootdemo.demoproject.model.User;
import com.webfactory.springbootdemo.demoproject.service.LocationService;
import com.webfactory.springbootdemo.demoproject.service.PostService;
import com.webfactory.springbootdemo.demoproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    @Autowired
    LocationService locationService;

    @GetMapping("/findUserByNickname/{nickname}")
    public User findByNickname(@PathVariable String nickname){
        return userService.findByNickname(nickname);
    }

    @GetMapping("/findUserByLocation/{locationCity}")
    public List<User> findByLocationCity(@PathVariable String city){
        return userService.findByLocationCity(city);
    }
}
