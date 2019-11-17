package com.ca.fire.test.mq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {

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
        String queueName = channel.queueDeclare().getQueue();
        String routeKey = "testRoutingKey";

        channel.queueBind(queueName, exchangeName, routeKey);

        while (true) {

            boolean autoAck = false;
            String consumerTag = "";
            channel.basicConsume(queueName, autoAck, consumerTag, new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String routingKey = envelope.getRoutingKey();
                    String contentType = properties.getContentType();
                    System.out.println("消费的路由键:" + routingKey);
                    System.out.println("消费的内容类型:" + contentType);

                    long deliveryTag = envelope.getDeliveryTag();
                    channel.basicAck(deliveryTag, false);
                    System.out.println("消息的内容:" );
                    String msg = new String(body, "UTF-8");
                    System.out.println(msg );
                }
            });
        }


    }
}
