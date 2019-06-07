package com.webfactory.springbootdemo.demoproject.service;

import com.webfactory.springbootdemo.demoproject.exeptions.post.exceptions.PostMissingParameterException;
import com.webfactory.springbootdemo.demoproject.exeptions.post.exceptions.PostNotFoundException;
import com.webfactory.springbootdemo.demoproject.exeptions.post.exceptions.PostParameterOutOfBoundException;
import com.webfactory.springbootdemo.demoproject.exeptions.user.exceptions.LocationMissingParameterException;
import com.webfactory.springbootdemo.demoproject.exeptions.user.exceptions.LocationParameterOutOfBoundException;
import com.webfactory.springbootdemo.demoproject.model.*;
import com.webfactory.springbootdemo.demoproject.model.reguest.bodies.PostForm;
import com.webfactory.springbootdemo.demoproject.model.reguest.bodies.PostModify;
import com.webfactory.springbootdemo.demoproject.model.reguest.bodies.PostResponse;
import com.webfactory.springbootdemo.demoproject.persistance.LocationRepository;
import com.webfactory.springbootdemo.demoproject.persistance.PostRepository;
import com.webfactory.springbootdemo.demoproject.persistance.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
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

    private void checkPostForm(PostForm postForm) throws PostMissingParameterException, PostParameterOutOfBoundException, LocationMissingParameterException, LocationParameterOutOfBoundException {
        checkTitle(postForm);
        checkDescription(postForm);
        checkPostLocation(postForm);
    }
    private void checkPostLocation(PostForm postForm) throws PostMissingParameterException, LocationMissingParameterException, LocationParameterOutOfBoundException {
        if (postForm.getLocation() == null)
            throw new PostMissingParameterException("Missing parameter location");
        {
            if (postForm.getLocation().getCity().equals(""))
                throw new LocationMissingParameterException("Missing parameter city");
            if(postForm.getLocation().getCity().length() > 120)
                throw new LocationParameterOutOfBoundException("City must to be smaller than 120 characters");
            if (postForm.getLocation().getCountry().equals(""))
                throw new LocationMissingParameterException("Missing parameter country");
            if (postForm.getLocation().getCountry().length() > 120)
                throw new LocationParameterOutOfBoundException("Country must to be smaller than 120 characters");
            if (postForm.getLocation().getLatitude().equals(""))
                throw new LocationMissingParameterException("Missing parameter latitude");
            if(postForm.getLocation().getLongitude() > 180 || postForm.getLocation().getLongitude()<-180)
                throw new LocationParameterOutOfBoundException("Longitude value must to be between -180 and 180!");
            if (postForm.getLocation().getLongitude().equals(""))
                throw new LocationMissingParameterException("Missing parameter longitude");
            if(postForm.getLocation().getLatitude() > 90 || postForm.getLocation().getLatitude() < -90)
                throw new LocationParameterOutOfBoundException("Latitude value must to be between -90 and 90!");
        }
    }

    private void checkDescription(PostForm postForm) throws PostMissingParameterException, PostParameterOutOfBoundException {
        if(postForm.getDescription().equals(""))
            throw new PostMissingParameterException("Missing parameter description");
        if(postForm.getDescription().length() > 1000)
            throw new PostParameterOutOfBoundException("Description must to be smaller than 1000 characters");
    }

    private void checkTitle(PostForm postForm) throws PostMissingParameterException, PostParameterOutOfBoundException {
        if(postForm.getTitle().equals(""))
            throw new PostMissingParameterException("Missing parameter title");
        if(postForm.getTitle().length() > 120)
            throw new PostParameterOutOfBoundException("Title must to be smaller than 120 characters");
    }
    public PostResponse createPost(PostForm postForm) throws PostMissingParameterException, PostParameterOutOfBoundException, LocationMissingParameterException, LocationParameterOutOfBoundException {

        checkPostForm(postForm);

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

        post.setUser(actualUser);
        post.setLocation(location);
        postRepository.save(post);

        locationRepository.save(location);
        userRepository.save(actualUser);

        PostResponse postResponse = new PostResponse();
        postResponse.setId(post.getId());
        postResponse.setTitle(postForm.getTitle());
        postResponse.setDescription(postForm.getDescription());
        postResponse.setLocation(location);
        postResponse.setUserEmail(postForm.getUser().getEmail());
        postResponse.setUserId(postForm.getUser().getId());

        return postResponse;
    }

    public Post updatePost(Long id, PostModify postModify, Principal principal) throws PostNotFoundException {
        String principal1 = principal.getName();
        System.out.println(principal1);

        User user = userRepository.findByUsername(principal1);

        System.out.println(user.getEmail());


        Optional<Post> post = postRepository.findById(id);
        if(!post.isPresent())
            throw new PostNotFoundException("Post not found!");
        Post actualPost = post.get();
        if (!postModify.getTitle().equals(""))
            actualPost.setTitle(postModify.getTitle());
        if (!postModify.getDescription().equals(""))
            actualPost.setDescription(postModify.getDescription());

        return postRepository.save(actualPost);
    }

    public List<Post> findAll() throws PostNotFoundException {
        List<Post> all = postRepository.findAll();
        if(all.size() == 0)
            throw new PostNotFoundException("No posts are found!");

        return all;
    }

    public Optional<Post> findPostById(Long id) throws PostNotFoundException {
        if(!postRepository.findById(id).isPresent())
            throw new PostNotFoundException("Post not found!");
        return postRepository.findById(id);
    }

    public void deletePost(Long id) throws PostNotFoundException {
        if(!postRepository.findById(id).isPresent())
            throw new PostNotFoundException("Post you want to delete does not exist!");
        postRepository.deleteById(id);
    }

    public List<Post> findByTitle(String postTitle) throws PostNotFoundException {
        List<Post> all = postRepository.findAllByTitle(postTitle);
//        all.stream().filter(post -> post.getTitle().equals(postTitle)).collect(Collectors.toList());
//        ;
        if(all.size() == 0)
            throw new PostNotFoundException("No posts are found with that title");
        return all;
    }

    public List<Post> findByLocation(Location location) throws PostNotFoundException {
        List<Post> all = postRepository.findAllByLocation(location);
//        all.stream().filter(post -> post.getLocation().getLatitude().equals(location.getLatitude()) &&
//                post.getLocation().getLongitude().equals(location.getLongitude()) &&
//                post.getLocation().getCountry().equals(location.getCity()) &&
//                post.getLocation().getCity().equals(location.getCity())).collect(Collectors.toList());
        if(all.size() == 0)
            throw new PostNotFoundException("No posts are found with that location");
        return all;
    }
}
