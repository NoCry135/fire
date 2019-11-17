package com.ca.fire.test.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {

    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setHost("localhost");
        connectionFactory.setVirtualHost("/");
        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();
        String exchangeName = "hello-exchange";
        channel.exchangeDeclare(exchangeName, "direct", true);
        String routeKey = "testRoutingKey";
        byte[] bytes = "quit".getBytes();
        channel.basicPublish(exchangeName, routeKey, null, bytes);
        channel.close();
        connection.close();
    }
}
