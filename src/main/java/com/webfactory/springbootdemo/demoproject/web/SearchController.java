package com.webfactory.springbootdemo.demoproject.web;

import com.webfactory.springbootdemo.demoproject.model.Location;
import com.webfactory.springbootdemo.demoproject.model.Post;
import com.webfactory.springbootdemo.demoproject.model.User;
import com.webfactory.springbootdemo.demoproject.service.PostService;
import com.webfactory.springbootdemo.demoproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SearchController {

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;


    @GetMapping("/findUserByNickname/{nickname}")
    @PreAuthorize("#oauth2.hasScope('read')")
    public List<User> findByNickname(@PathVariable String nickname) {
        return userService.findByNickname(nickname);
    }

    @GetMapping("/findUserByLocation/{locationCity}")
    @PreAuthorize("#oauth2.hasScope('read')")
    public List<User> findByLocationCity(@PathVariable String locationCity) {
        return userService.findByLocationCity(locationCity);
    }

    @GetMapping("/findPostByTitle/{postTitle}")
    @PreAuthorize("#oauth2.hasScope('read')")
    public List<Post> findByTitle(@PathVariable String postTitle) {
        return postService.findByTitle(postTitle);
    }

    @PostMapping("/findPostByLocation")
    @PreAuthorize("#oauth2.hasScope('read')")
    public List<Post> findByLocation(@RequestBody Location location) {
        return postService.findByLocation(location);
    }
}
