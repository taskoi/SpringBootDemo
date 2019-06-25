package com.webfactory.springbootdemo.demoproject.events;

import com.webfactory.springbootdemo.demoproject.model.Post;
import org.springframework.context.ApplicationEvent;

public class FindPostByIdEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */

    private Post post;

    public FindPostByIdEvent(Object source, Post post) {
        super(source);
        this.post = post;
    }

    public Post getPost() {
        return post;
    }
}
