package com.example.topic;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicConsumer {

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "topicExchange",type = ExchangeTypes.TOPIC)
                    ,key = { "hello.*" }
            )
    })
    public void topicConsume1(String body){
        System.out.println("topicConsume1 -- " + body);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "topicExchange",type = ExchangeTypes.TOPIC)
                    ,key = { "hello.#" }
            )
    })
    public void topicConsume2(String body){
        System.out.println("topicConsume2 -- " + body);
    }

}
