package com.ca.fire.test.mq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class FooMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        String str = new String(message.getBody());
        System.out.println("receive:" + str);
    }
}
