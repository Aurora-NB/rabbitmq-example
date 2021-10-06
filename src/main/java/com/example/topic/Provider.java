package com.example.topic;

import com.example.utils.RabbitMQConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import lombok.SneakyThrows;

import java.nio.charset.StandardCharsets;

public class Provider {

    @SneakyThrows
    public static void main(String[] args) {
        final String exchangeName = "topic";
        final String routingKey = "log.hello.test";
        Connection connection = RabbitMQConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(exchangeName, "topic");
        channel.basicPublish(exchangeName, routingKey, null,(exchangeName + "-" + routingKey).getBytes(StandardCharsets.UTF_8));
        RabbitMQConnectionUtil.closeResource(channel,connection);
    }

}
