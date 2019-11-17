package com.ca.fire.listener;

import com.ca.fire.domain.bean.User;
import com.ca.fire.event.LoginEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class LoginListener implements SmartApplicationListener {

    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        return eventType == LoginEvent.class;
    }

    @Override
    public boolean supportsSourceType(Class<?> sourceType) {
        return sourceType == User.class;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        User source = (User) event.getSource();
        System.out.println("监听用户登录:user," + source.toString());

    }

    @Override
    public int getOrder() {
        return 0;
    }
}