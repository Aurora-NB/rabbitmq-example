package com.example.route;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RouteConsumer {

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "directExchange", type = "direct"),
                    key={"info","warn","error"}
            )
    })
    public void routeConsumer1(String body){
        System.out.println("routeConsumer1 -- " + body);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "directExchange", type = "direct"),
                    key={"error"}
            )
    })
    public void routeConsumer2(String body){
        System.out.println("routeConsumer2 -- " + body);
    }

}
