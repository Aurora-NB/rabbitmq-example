package com.example.fanout;

import com.example.utils.RabbitMQConnectionUtil;
import com.rabbitmq.client.*;
import lombok.SneakyThrows;

import java.io.IOException;

public class Consumer3 {
    @SneakyThrows
    public static void main(String[] args) {
        Connection connection = RabbitMQConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("registry", "fanout");
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName,"registry","");
        channel.basicConsume(queueName, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(new String(body));
            }
        });

    }
}
