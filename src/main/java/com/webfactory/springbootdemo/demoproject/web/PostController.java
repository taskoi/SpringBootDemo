package com.webfactory.springbootdemo.demoproject.web;

import com.webfactory.springbootdemo.demoproject.model.Post;
import com.webfactory.springbootdemo.demoproject.model.PostForm;
import com.webfactory.springbootdemo.demoproject.model.PostModify;
import com.webfactory.springbootdemo.demoproject.model.PostResponse;
import com.webfactory.springbootdemo.demoproject.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@Transactional(propagation = Propagation.REQUIRED)
public class PostController {

    @Autowired
    PostService postService;


    @PostMapping("/createPost")
    public PostResponse createPost(@Valid @RequestBody PostForm postForm) {
        return postService.createPost(postForm);
    }

    @PatchMapping("/updatePost/{id}")
    public Post updatePost(@Valid @PathVariable Long id, @RequestBody PostModify postModify) {
        return postService.updatePost(id, postModify);
    }

    @GetMapping("/findAllPosts")
    public List<Post> findAll() {
        return postService.findAll();
    }

    @GetMapping("/findPostById/{id}")
    public Optional<Post> findPost(@PathVariable Long id) {
        return postService.findPostById(id);
    }

    @DeleteMapping("/deletePost/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }
}
