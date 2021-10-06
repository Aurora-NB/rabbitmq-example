package com.example.work;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class WorkConsumer {
    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void consumer1(String body){
        System.out.println("consumer1-"+ body);
    }

    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void consumer2(String body){
        System.out.println("consumer2-"+ body);
    }
}
