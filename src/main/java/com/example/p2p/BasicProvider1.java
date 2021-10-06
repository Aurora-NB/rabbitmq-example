package com.example.p2p;

import com.example.utils.RabbitMQConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import lombok.SneakyThrows;
import lombok.Synchronized;
import org.junit.jupiter.api.Test;

public class BasicProvider1 {

    @Test
    @SneakyThrows
    public void testSend(){
        Connection connection = RabbitMQConnectionUtil.getConnection();
        assert connection != null;
        Channel channel = connection.createChannel();
        channel.queueDeclare("hello",false,false,true,null);
        /*-
        * 生产消息
        * 参数1:交换机
        * 参数2:队列名称
        * 参数3:额外参数
        *   MessageProperties.PERSISTENT_TEXT_PLAIN 持久化消息
        *
        * 参数4:要发送的消息
        * */

        channel.basicPublish("","hello",null,"hello world".getBytes());
        channel.close();
        connection.close();
    }

}
