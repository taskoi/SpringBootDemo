package com.webfactory.springbootdemo.demoproject.events;

import com.webfactory.springbootdemo.demoproject.model.User;
import org.springframework.context.ApplicationEvent;

import java.util.ArrayList;
import java.util.List;

public class FindAllUsersEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */

    private List<User> users;


    public FindAllUsersEvent(Object source, List<User> users) {
        super(source);
        this.users = new ArrayList<>(users);
    }

    public List<User> getUsers() {
        return users;
    }
}
