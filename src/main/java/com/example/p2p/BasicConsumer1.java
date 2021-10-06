package com.example.p2p;

import com.example.utils.RabbitMQConnectionUtil;
import com.rabbitmq.client.*;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class BasicConsumer1 {

    @SneakyThrows
    public static void main(String[] args) {
        testConsume();
    }

    @SneakyThrows
    public static void testConsume(){

        Connection connection = RabbitMQConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("hello",false,false,true,null);
        /*
        * 消费消息
        * 参数1:队列名称
        * 参数2:是否开启ack验证
        * 参数3:回调
        * */
        channel.basicConsume("hello", false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(new String(body));
            }
        });

    }
}
