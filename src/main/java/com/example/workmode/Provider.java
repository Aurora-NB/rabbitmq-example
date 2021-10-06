package com.example.workmode;

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
        channel.queueDeclare("work", true, false, false, null);
        for (int i = 0; i < 20; i++) {
            channel.basicPublish("", "work", MessageProperties.PERSISTENT_TEXT_PLAIN,(i + "hello world").getBytes(StandardCharsets.UTF_8));
        }
//        RabbitMQConnectionUtil.closeResource(connection,channel);

    }
}
