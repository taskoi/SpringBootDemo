package com.webfactory.springbootdemo.demoproject.web;

        import com.webfactory.springbootdemo.demoproject.exeptions.post.exceptions.PostMissingParameterException;
        import com.webfactory.springbootdemo.demoproject.exeptions.post.exceptions.PostNotFoundException;
        import com.webfactory.springbootdemo.demoproject.exeptions.post.exceptions.PostParameterOutOfBoundException;
        import com.webfactory.springbootdemo.demoproject.exeptions.user.exceptions.LocationMissingParameterException;
        import com.webfactory.springbootdemo.demoproject.exeptions.user.exceptions.LocationParameterOutOfBoundException;
        import com.webfactory.springbootdemo.demoproject.model.*;
        import com.webfactory.springbootdemo.demoproject.model.reguest.bodies.PostForm;
        import com.webfactory.springbootdemo.demoproject.model.reguest.bodies.PostModify;
        import com.webfactory.springbootdemo.demoproject.model.reguest.bodies.PostResponse;
        import com.webfactory.springbootdemo.demoproject.service.PostService;
        import com.webfactory.springbootdemo.demoproject.service.UserService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.security.access.prepost.PreAuthorize;
        import org.springframework.security.oauth2.provider.OAuth2Authentication;
        import org.springframework.transaction.annotation.Propagation;
        import org.springframework.transaction.annotation.Transactional;
        import org.springframework.web.bind.annotation.*;

        import javax.validation.Valid;
        import java.security.Principal;
        import java.util.List;
        import java.util.Optional;

@RestController
@Transactional(propagation = Propagation.REQUIRED)
@RequestMapping("/api")
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    @PreAuthorize("#oauth2.hasScope('write')")
    @PostMapping("/createPost")
    public PostResponse createPost(@Valid @RequestBody PostForm postForm, OAuth2Authentication authentication) throws PostMissingParameterException, PostParameterOutOfBoundException, LocationMissingParameterException, LocationParameterOutOfBoundException {
        return postService.createPost(postForm);
    }

    @PreAuthorize("#oauth2.hasScope('write')")
    @PatchMapping("/updatePost/{id}")
    public Post updatePost(@Valid @PathVariable Long id, @RequestBody PostModify postModify, Principal principal) throws PostNotFoundException {
        return postService.updatePost(id, postModify,principal);
    }

    @PreAuthorize("#oauth2.hasScope('read')")
    @GetMapping("/findAllPosts")
    public List<Post> findAll() throws PostNotFoundException {
        return postService.findAll();
    }

    @PreAuthorize("#oauth2.hasScope('read')")
    @GetMapping("/findPostById/{id}")
    public Optional<Post> findPost(@PathVariable Long id) throws PostNotFoundException {
        return postService.findPostById(id);
    }

    @DeleteMapping("/deletePost/{id}")
    @PreAuthorize("#oauth2.hasScope('write')")
    public void deletePost(@PathVariable Long id) throws PostNotFoundException {
        postService.deletePost(id);
    }
}
