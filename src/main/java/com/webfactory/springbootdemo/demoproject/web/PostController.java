package com.webfactory.springbootdemo.demoproject.web;


import com.webfactory.springbootdemo.demoproject.exeptions.post.exceptions.PostNotFoundException;
import com.webfactory.springbootdemo.demoproject.exeptions.post.exceptions.UserIsNotOwnerException;
import com.webfactory.springbootdemo.demoproject.exeptions.user.exceptions.UserNotFoundException;
import com.webfactory.springbootdemo.demoproject.model.*;
import com.webfactory.springbootdemo.demoproject.model.reguest.bodies.PostForm;
import com.webfactory.springbootdemo.demoproject.model.reguest.bodies.PostModify;
import com.webfactory.springbootdemo.demoproject.model.reguest.bodies.PostResponse;
import com.webfactory.springbootdemo.demoproject.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;

@RestController
@Transactional(propagation = Propagation.REQUIRED)
@RequestMapping("/api/post")
@Api(value = "demoproject")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @ApiOperation(value = "Create a post")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created post"),
            @ApiResponse(code = 401, message = "You are not authorized to use this resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PreAuthorize("#oauth2.hasScope('write')")
    @PostMapping("/createPost")
    public PostResponse createPost(@Valid @RequestBody PostForm postForm, OAuth2Authentication authentication) throws UserNotFoundException {
        return postService.createPost(postForm);
    }

    @ApiOperation(value = "Update a post")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated post"),
            @ApiResponse(code = 401, message = "You are not authorized to use this resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PreAuthorize("#oauth2.hasScope('write')")
    @PatchMapping("/updatePost/{id}")
    public Post updatePost(@PathVariable Long id, @RequestBody PostModify postModify, Principal principal) throws PostNotFoundException, UserIsNotOwnerException {

        return postService.updatePost(id, postModify, principal);
    }

    @ApiOperation(value = "View a list of all posts")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrived list"),
            @ApiResponse(code = 401, message = "You are not authorized to use this resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PreAuthorize("#oauth2.hasScope('read')")
    @GetMapping("/findAllPosts")
    public Page<Post> findAll(Pageable pageable) throws PostNotFoundException {
        return postService.findAll(pageable);
    }

    @ApiOperation(value = "Search for a post by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrived post"),
            @ApiResponse(code = 401, message = "You are not authorized to use this resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PreAuthorize("#oauth2.hasScope('read')")
    @GetMapping("/findPostById/{id}")
    public Optional<Post> findPost(@PathVariable Long id) throws PostNotFoundException {
        return postService.findPostById(id);
    }

    @ApiOperation(value = "Delete a post by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created user"),
            @ApiResponse(code = 401, message = "You are not authorized to use this resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @DeleteMapping("/deletePost/{id}")
    @PreAuthorize("#oauth2.hasScope('write')")
    public void deletePost(@PathVariable Long id) throws PostNotFoundException {
        postService.deletePost(id);
    }
}
