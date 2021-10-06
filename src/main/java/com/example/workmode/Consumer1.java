package com.example.workmode;

import com.example.utils.RabbitMQConnectionUtil;
import com.rabbitmq.client.*;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Consumer1 {

    @SneakyThrows
    public static void main(String[] args) {
        Connection connection = RabbitMQConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        /*每次默认消费一个消息*/
        channel.basicQos(1);
        channel.queueDeclare("work", true, false, false, null);
        channel.basicConsume("work", false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者1--" + new String(body));
                /*消息确认*/
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });
    }
}
