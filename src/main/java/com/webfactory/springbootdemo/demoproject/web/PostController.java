package com.webfactory.springbootdemo.demoproject.web;

        import com.webfactory.springbootdemo.demoproject.model.*;
        import com.webfactory.springbootdemo.demoproject.service.PostService;
        import com.webfactory.springbootdemo.demoproject.service.UserService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.security.access.prepost.PreAuthorize;
        import org.springframework.security.authentication.AbstractAuthenticationToken;
        import org.springframework.security.core.Authentication;
        import org.springframework.security.core.context.SecurityContextHolder;
        import org.springframework.security.core.parameters.P;
        import org.springframework.security.core.userdetails.UserDetails;
        import org.springframework.security.oauth2.provider.OAuth2Authentication;
        import org.springframework.transaction.annotation.Propagation;
        import org.springframework.transaction.annotation.Transactional;
        import org.springframework.web.bind.annotation.*;
        import org.springframework.web.client.HttpClientErrorException;

        import javax.validation.Valid;
        import java.security.Principal;
        import java.util.List;
        import java.util.Optional;

@RestController
@Transactional(propagation = Propagation.REQUIRED)
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    @PreAuthorize("#oauth2.hasScope('write')")
    @PostMapping("/createPost")
    public PostResponse createPost(@Valid @RequestBody PostForm postForm, OAuth2Authentication authentication) {
        return postService.createPost(postForm);
    }

    @PatchMapping("/updatePost/{id}")
    public Post updatePost(@Valid @PathVariable Long id, @RequestBody PostModify postModify, Principal principal) {
        return postService.updatePost(id, postModify,principal);
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
