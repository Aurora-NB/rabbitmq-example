package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Provider {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    public void hello() {
        rabbitTemplate.convertAndSend("hello", "Hello world");
    }

    @Test
    public void work() {
        for (int i = 0; i < 20; i++) {
            rabbitTemplate.convertAndSend("work", "work" + i);
        }
    }

    @Test
    public void fanout() {
        rabbitTemplate.convertAndSend("fanoutExchange", "", "fanout");
    }

    @Test
    public void route() {
        rabbitTemplate.convertAndSend("directExchange", "info", "info-direct");
        rabbitTemplate.convertAndSend("directExchange", "error", "error-direct");
    }

    @Test
    public void topic() {
        rabbitTemplate.convertAndSend("topicExchange", "hello.world", "hello.world...");
        rabbitTemplate.convertAndSend("topicExchange", "hello.world.test", "hello.world.test...");
    }

}
