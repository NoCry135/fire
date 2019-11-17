package com.ca.fire.test.mq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SendMessage {
    public static void main(String[] args) {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-mq.xml");
        RabbitTemplate bean = context.getBean(RabbitTemplate.class);

        bean.convertAndSend("hello world");
        context.close();
    }
}
