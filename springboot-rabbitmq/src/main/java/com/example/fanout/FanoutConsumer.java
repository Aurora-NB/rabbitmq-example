package com.example.fanout;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FanoutConsumer {

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "fanoutExchange", type = "fanout")
            )
    })
    public void fanoutConsumer1(String body){
        System.out.println("fanoutConsumer1 -- " + body);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "fanoutExchange", type = "fanout")
            )
    })
    public void fanoutConsumer2(String body){
        System.out.println("fanoutConsumer2 -- " + body);
    }
}
