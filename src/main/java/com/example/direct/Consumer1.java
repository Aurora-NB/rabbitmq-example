package com.example.direct;

import com.example.utils.RabbitMQConnectionUtil;
import com.rabbitmq.client.*;
import lombok.SneakyThrows;

import java.io.IOException;

public class Consumer1 {

    @SneakyThrows
    public static void main(String[] args) {

        final String exchangeName = "direct";
        Connection connection = RabbitMQConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(exchangeName, "direct");
        String queue = channel.queueDeclare().getQueue();
        channel.queueBind(queue,exchangeName,"info");
        channel.queueBind(queue,exchangeName,"warning");
        channel.queueBind(queue,exchangeName,"error");
        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(new String(body));
            }
        });
    }
}
