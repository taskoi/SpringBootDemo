package com.webfactory.springbootdemo.demoproject.events;

import org.springframework.context.ApplicationEvent;

public class DeletePostEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */

    private Long id;

    public DeletePostEvent(Object source, Long id) {
        super(source);
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
