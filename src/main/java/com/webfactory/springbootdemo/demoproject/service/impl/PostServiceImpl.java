package com.webfactory.springbootdemo.demoproject.service.impl;

import com.webfactory.springbootdemo.demoproject.events.*;
import com.webfactory.springbootdemo.demoproject.exeptions.post.exceptions.PostNotFoundException;
import com.webfactory.springbootdemo.demoproject.exeptions.post.exceptions.UserIsNotOwnerException;
import com.webfactory.springbootdemo.demoproject.exeptions.user.exceptions.UserNotFoundException;
import com.webfactory.springbootdemo.demoproject.model.*;
import com.webfactory.springbootdemo.demoproject.model.reguest.bodies.PostForm;
import com.webfactory.springbootdemo.demoproject.model.reguest.bodies.PostModify;
import com.webfactory.springbootdemo.demoproject.model.reguest.bodies.PostResponse;
import com.webfactory.springbootdemo.demoproject.persistance.LocationRepository;
import com.webfactory.springbootdemo.demoproject.persistance.PostRepository;
import com.webfactory.springbootdemo.demoproject.persistance.UserRepository;
import com.webfactory.springbootdemo.demoproject.service.PostService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private final
    PostRepository postRepository;

    private final
    UserRepository userRepository;

    private final
    LocationRepository locationRepository;

    private final
    ApplicationEventPublisher applicationEventPublisher;

    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository, LocationRepository locationRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.locationRepository = locationRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public PostResponse createPost(PostForm postForm) throws UserNotFoundException {

        Optional<User> user = userRepository.findById(postForm.getUser().getId());
        User actualUser;

        if (user.isPresent()) {
            actualUser = user.get();
        } else {
            throw new UserNotFoundException(postForm.getUser().getNickname());
        }

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

        post.setUser(actualUser);
        post.setLocation(location);
        postRepository.save(post);

        locationRepository.save(location);
        userRepository.save(actualUser);

        applicationEventPublisher.publishEvent(new CreatePostEvent(this, post, actualUser));

        PostResponse postResponse = new PostResponse();
        postResponse.setId(post.getId());
        postResponse.setTitle(postForm.getTitle());
        postResponse.setDescription(postForm.getDescription());
        postResponse.setLocation(location);
        postResponse.setUserEmail(postForm.getUser().getEmail());
        postResponse.setUserId(postForm.getUser().getId());

        return postResponse;
    }

    public Post updatePost(Long id, PostModify postModify, Principal principal) throws PostNotFoundException, UserIsNotOwnerException {
        //principal name is actually users username;
        String principalName = principal.getName();
        int princi = principal.hashCode();
        System.out.println("Principal" + principalName + " " + princi);

        User user = userRepository.findByUsername(principalName);

        System.out.println(user.getId());

        Optional<Post> post = postRepository.findById(id);
        if (!post.isPresent())
            throw new PostNotFoundException("Post not found!");
        if (post.get().getUser().getId().equals(user.getId())) {
            Post actualPost = post.get();
            if (!postModify.getTitle().equals(""))
                actualPost.setTitle(postModify.getTitle());
            if (!postModify.getDescription().equals(""))
                actualPost.setDescription(postModify.getDescription());

            applicationEventPublisher.publishEvent(new UpdatePostEvent(this, actualPost, user));

            return postRepository.save(actualPost);
        }
        throw new UserIsNotOwnerException(post.get().getId());
    }

    public Page<Post> findAll(Pageable pageable) throws PostNotFoundException {
        Page<Post> all = postRepository.findAll(pageable);
        if (all.getSize() == 0)
            throw new PostNotFoundException("No posts are found!");
        else {
            List<Post> posts = postRepository.findAll();
            applicationEventPublisher.publishEvent(new FindAllPostsEvent(this, posts));
            return all;
        }
    }

    public Optional<Post> findPostById(Long id) throws PostNotFoundException {
        if (!postRepository.findById(id).isPresent())
            throw new PostNotFoundException("Post not found!");
        else {
            applicationEventPublisher.publishEvent(new FindPostByIdEvent(this, postRepository.findById(id).get()));
            return postRepository.findById(id);
        }
    }

    public void deletePost(Long id) throws PostNotFoundException {
        if (!postRepository.findById(id).isPresent())
            throw new PostNotFoundException("Post you want to delete does not exist!");
        else {
            applicationEventPublisher.publishEvent(new DeletePostEvent(this, id));
            postRepository.deleteById(id);
        }
    }

    public Page<Post> findByTitle(String postTitle, Pageable pageable) throws PostNotFoundException {
        Page<Post> all = postRepository.findAllByTitle(pageable, postTitle);

        if (all.getSize() == 0)
            throw new PostNotFoundException("No posts are found with that title");
        else {
            List<Post> posts = postRepository.findAllByTitle(postTitle);
            applicationEventPublisher.publishEvent(new FindAllPostsByTitle(this, posts));
            return all;
        }
    }

    public Page<Post> findByLocation(Location location, Pageable pageable) throws PostNotFoundException {
        Page<Post> all = postRepository.findAllByLocation(pageable, location);

        if (all.getSize() == 0)
            throw new PostNotFoundException("No posts are found with that location");
        else {
            List<Post> posts = postRepository.findAllByLocation(location);
            applicationEventPublisher.publishEvent(new FindAllPostsByLocationEvent(this, posts));
            return all;
        }
    }
}
