package com.webfactory.springbootdemo.demoproject.web;

import com.webfactory.springbootdemo.demoproject.exeptions.post.exceptions.PostNotFoundException;
import com.webfactory.springbootdemo.demoproject.exeptions.user.exceptions.UserNotFoundException;
import com.webfactory.springbootdemo.demoproject.model.Location;
import com.webfactory.springbootdemo.demoproject.model.Post;
import com.webfactory.springbootdemo.demoproject.model.User;
import com.webfactory.springbootdemo.demoproject.service.PostService;
import com.webfactory.springbootdemo.demoproject.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/search")
@Api(value = "demoproject")
public class SearchController {

    private final PostService postService;

    private final UserService userService;

    public SearchController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @ApiOperation(value = "Search users by nickname")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrived list"),
            @ApiResponse(code = 401, message = "You are not authorized to use this resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("/findUserByNickname/{nickname}")
    @PreAuthorize("#oauth2.hasScope('read')")
    public Page<User> findByNickname(@PathVariable String nickname, Pageable pageable) throws UserNotFoundException {
        return userService.findByNickname(pageable, nickname);
    }

    @ApiOperation(value = "Search users by location city")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrived list"),
            @ApiResponse(code = 401, message = "You are not authorized to use this resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("/findUserByLocation/{locationCity}")
    @PreAuthorize("#oauth2.hasScope('read')")
    public Page<User> findByLocationCity(@PathVariable String locationCity, Pageable pageable) throws UserNotFoundException {
        return userService.findByLocationCity(pageable, locationCity);
    }

    @ApiOperation(value = "Search posts by post title")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrived list"),
            @ApiResponse(code = 401, message = "You are not authorized to use this resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("/findPostByTitle/{postTitle}")
    @PreAuthorize("#oauth2.hasScope('read')")
    public Page<Post> findByTitle(@PathVariable String postTitle, Pageable pageable) throws PostNotFoundException {
        return postService.findByTitle(postTitle, pageable);
    }

    @ApiOperation(value = "Search posts by location")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrived list"),
            @ApiResponse(code = 401, message = "You are not authorized to use this resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PostMapping("/findPostByLocation")
    @PreAuthorize("#oauth2.hasScope('read')")
    public Page<Post> findByLocation(@RequestBody Location location, Pageable pageable) throws PostNotFoundException {
        return postService.findByLocation(location, pageable);
    }
}
