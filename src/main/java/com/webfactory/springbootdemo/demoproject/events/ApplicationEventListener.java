package com.webfactory.springbootdemo.demoproject.events;

import com.webfactory.springbootdemo.demoproject.model.Log;
import com.webfactory.springbootdemo.demoproject.persistance.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ApplicationEventListener {

    private final LogRepository logRepository;

    public ApplicationEventListener(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @EventListener(CreateUserEvent.class)
    public void handleCreteUserEvent(CreateUserEvent event) {
        Log log = new Log("Create user method called", "User with email " + event.getUser().getEmail() + " has been created");
        logRepository.insert(log);
    }

    @EventListener(UpdateUserEvent.class)
    public void handleUpdateUserEvent(UpdateUserEvent event) {
        Log log = new Log("Update user method called", "User with email " + event.getUser().getEmail() + " has been updated");
        logRepository.save(log);
    }

    @EventListener(DeleteUserEvent.class)
    public void handleDeleteUserEvent(DeleteUserEvent event) {
        Log log = new Log("Delete user method called", "User with id " + event.getId() + " has been deleted");
        logRepository.save(log);
    }

    @EventListener(FindAllUsersByLocationCityEvent.class)
    public void handleFindAllUsersByLocationCityEvent(FindAllUsersByLocationCityEvent event) {
        Log log = new Log("Find all users by location city method called", "Returned list size is " + event.getUsers().size());
        logRepository.save(log);
    }

    @EventListener(FindAllUsersByNicknameEvent.class)
    public void handleFindAllUsersByNicknameEvent(FindAllUsersByNicknameEvent event) {
        Log log = new Log("Find all users by nickname method called", "Returned list size is " + event.getUsers().size());
        logRepository.save(log);
    }

    @EventListener(FindAllUsersByUsernameEvent.class)
    public void handleFindAllUsersByUsernameEvent(FindAllUsersByUsernameEvent event) {
        Log log = new Log("Find all users by username method called", "Returned list size is " + event.getUsers().size());
        logRepository.save(log);
    }

    @EventListener(FindAllUsersEvent.class)
    public void handleFindAllUsersEvent(FindAllUsersEvent event) {
        Log log = new Log("Find all users method called", "Returned list size is " + event.getUsers().size());
        logRepository.save(log);
    }

    @EventListener(CreatePostEvent.class)
    public void handleCreatePostEvent(CreatePostEvent event) {
        Log log = new Log("Create post method called", "User with username " + event.getUser().getUsername() + " has created post with title " + event.getPost().getTitle());
        logRepository.save(log);
    }

    @EventListener(UpdatePostEvent.class)
    public void handleUpdatePostEvent(UpdatePostEvent event) {
        Log log = new Log("Update post method called", "User with username " + event.getUser().getUsername() + " has created post with title " + event.getPost().getTitle());
        logRepository.save(log);
    }

    @EventListener(DeletePostEvent.class)
    public void handleDeletePostEvenet(DeletePostEvent event) {
        Log log = new Log("Delete post method called", "Post with id " + event.getId() + " has been deleted");
        logRepository.save(log);
    }

    @EventListener(FindAllPostsByLocationEvent.class)
    public void handleFindAllPostsByLocationEvent(FindAllPostsByLocationEvent event) {
        Log log = new Log("Find all posts by location method called", "Size of the returned list is " + event.getPosts().size());
        logRepository.save(log);
    }

    @EventListener(FindAllPostsByTitle.class)
    public void handleFindAllPostsByTitle(FindAllPostsByTitle event) {
        Log log = new Log("Find all posts by title method called", "Size of the returned list is " + event.getPosts().size());
        logRepository.save(log);
    }

    @EventListener(FindAllPostsEvent.class)
    public void handleFindAllPostsEvent(FindAllPostsEvent event) {
        Log log = new Log("Find all posts method called", "Size of the returned list is " + event.getPosts().size());
        logRepository.save(log);
    }

    @EventListener(FindPostByIdEvent.class)
    public void handleFindPostByIdEvent(FindPostByIdEvent event) {
        saveLog("Find post by id method called", "The post with id " + event.getPost().getId() + " and with tile " + event.getPost().getTitle() + " was returned");
    }

    private void saveLog(String type, String desciption) {
        Log log = new Log(type, desciption);
        logRepository.save(log);
    }
}
