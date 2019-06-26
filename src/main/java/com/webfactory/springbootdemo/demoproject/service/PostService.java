package com.webfactory.springbootdemo.demoproject.service;

import com.webfactory.springbootdemo.demoproject.exeptions.post.exceptions.PostNotFoundException;
import com.webfactory.springbootdemo.demoproject.exeptions.post.exceptions.UserIsNotOwnerException;
import com.webfactory.springbootdemo.demoproject.exeptions.user.exceptions.UserNotFoundException;
import com.webfactory.springbootdemo.demoproject.model.Location;
import com.webfactory.springbootdemo.demoproject.model.Post;
import com.webfactory.springbootdemo.demoproject.model.reguest.bodies.PostForm;
import com.webfactory.springbootdemo.demoproject.model.reguest.bodies.PostModify;
import com.webfactory.springbootdemo.demoproject.model.reguest.bodies.PostResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.security.Principal;
import java.util.Optional;

public interface PostService {

    PostResponse createPost(PostForm postForm) throws UserNotFoundException;

    Post updatePost(Long id, PostModify postModify, Principal principal) throws PostNotFoundException, UserIsNotOwnerException;

    Page<Post> findAll(Pageable pageable) throws PostNotFoundException;

    Optional<Post> findPostById(Long id) throws PostNotFoundException;

    void deletePost(Long id) throws PostNotFoundException;

    Page<Post> findByTitle(String postTitle, Pageable pageable) throws PostNotFoundException;

    Page<Post> findByLocation(Location location, Pageable pageable) throws PostNotFoundException;

}
