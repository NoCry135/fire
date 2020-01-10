package com.ca.fire.listener;

import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by caian on 2019/11/25
 */
public class MyServletContextListener implements ServletContextListener {

    private String text;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        text = "111";
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
