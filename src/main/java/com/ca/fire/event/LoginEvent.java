package com.ca.fire.event;

import com.ca.fire.domain.bean.User;
import org.springframework.context.ApplicationEvent;

public class LoginEvent extends ApplicationEvent {


    /**
     * Create a new ApplicationEvent.
     *
     * @param user the object on which the event initially occurred (never {@code null})
     */
    public LoginEvent(User user) {
        super(user);
    }
}
