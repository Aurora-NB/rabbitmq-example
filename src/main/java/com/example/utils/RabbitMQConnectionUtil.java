package com.example.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.SneakyThrows;

public class RabbitMQConnectionUtil {

    private static final ConnectionFactory CONNECTION_FACTORY;

    static {
        CONNECTION_FACTORY = new ConnectionFactory();
        CONNECTION_FACTORY.setHost("10.20.5.37");
        CONNECTION_FACTORY.setPort(5672);
        CONNECTION_FACTORY.setVirtualHost("/ems");
        CONNECTION_FACTORY.setUsername("ems");
        CONNECTION_FACTORY.setPassword("ems");
    }

    @SneakyThrows
    public static Connection getConnection() {
        return CONNECTION_FACTORY.newConnection();
    }

    @SneakyThrows
    public static void closeResource( Channel channel,Connection connection) {
        channel.close();
        connection.close();
    }
}
