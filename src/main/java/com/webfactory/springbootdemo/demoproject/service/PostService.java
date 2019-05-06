package com.webfactory.springbootdemo.demoproject.service;

import com.webfactory.springbootdemo.demoproject.model.Location;
import com.webfactory.springbootdemo.demoproject.model.Post;
import com.webfactory.springbootdemo.demoproject.model.PostForm;
import com.webfactory.springbootdemo.demoproject.model.User;
import com.webfactory.springbootdemo.demoproject.persistance.LocationRepository;
import com.webfactory.springbootdemo.demoproject.persistance.PostRepository;
import com.webfactory.springbootdemo.demoproject.persistance.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    UserService userService;


    public Post createPost(PostForm postForm){
        Optional<User> user = userRepository.findById(postForm.getUser().getId());
        User actualUser = user.get();

        Post post = new Post();
        Location location = new Location();

        post.setTitle(postForm.getTitle());
        post.setDescription(postForm.getDescription());

        actualUser.getUserPostsList().add(post);

        location.setCountry(postForm.getLocation().getCountry());
        location.setLatitude(postForm.getLocation().getLatitude());
        location.setLongitude(postForm.getLocation().getLongitude());
        location.setCity(postForm.getLocation().getCity());


        actualUser.getUserPostsList().add(post);
        actualUser.getLocation().getLocationPostsList().add(post);
        locationRepository.save(post.getLocation());
        userRepository.save(actualUser);

        return post;
    }

    public Post updatePost(Long id, PostForm postForm){
        Optional<Post> post = postRepository.findById(id);
        Post actualPost = post.get();

        if(postForm.getDescription() != null)
            actualPost.setDescription(postForm.getDescription());
        if(postForm.getTitle() != null)
            actualPost.setTitle(postForm.getTitle());

        System.out.println(post);
        System.out.println(actualPost);

        return postRepository.save(actualPost);
    }

    public List<Post> findAll(){
        return postRepository.findAll();
    }

    public Optional<Post> findPostById(Long id){
        return postRepository.findById(id);
    }

    public void deletePost(Long id){
        postRepository.deleteById(id);
    }
}
