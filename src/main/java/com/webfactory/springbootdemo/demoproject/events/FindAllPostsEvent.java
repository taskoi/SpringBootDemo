package com.webfactory.springbootdemo.demoproject.events;

import com.webfactory.springbootdemo.demoproject.model.Post;
import org.springframework.context.ApplicationEvent;

import java.util.ArrayList;
import java.util.List;

public class FindAllPostsEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */

    private List<Post> posts;
    public FindAllPostsEvent(Object source,List<Post> posts) {
        super(source);
        this.posts = new ArrayList<>(posts);
    }

    public List<Post> getPosts() {
        return posts;
    }
}
