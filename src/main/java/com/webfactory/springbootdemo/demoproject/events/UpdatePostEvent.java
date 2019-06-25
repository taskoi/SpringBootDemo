package com.webfactory.springbootdemo.demoproject.events;

import com.webfactory.springbootdemo.demoproject.model.Post;
import com.webfactory.springbootdemo.demoproject.model.User;
import org.springframework.context.ApplicationEvent;

public class UpdatePostEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */

    private Post post;
    private User user;

    public UpdatePostEvent(Object source, Post post, User user) {
        super(source);
        this.post = post;
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public User getUser() {
        return user;
    }
}
