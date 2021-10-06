package com.example.fanout;

import com.example.utils.RabbitMQConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import lombok.SneakyThrows;

import java.nio.charset.StandardCharsets;

public class Provider {
    @SneakyThrows
    public static void main(String[] args) {
        Connection connection = RabbitMQConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("registry", "fanout");
        channel.basicPublish("registry", "", MessageProperties.PERSISTENT_TEXT_PLAIN, "hello".getBytes(StandardCharsets.UTF_8));

    }
}
