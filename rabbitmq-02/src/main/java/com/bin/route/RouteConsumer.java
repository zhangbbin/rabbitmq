package com.bin.route;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RouteConsumer {

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,//创建队列
                    exchange = @Exchange(value = "directs", type = "direct"),//指定交换机名称和类型
                    key = {"info","error", "warn"} //指定接收类型
            )
    })
    public void receive1(String message) {
        System.out.println("message1 = " + message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,//创建队列
                    exchange = @Exchange(value = "directs", type = "direct"),//指定交换机名称和类型
                    key = {"error"} //指定接收类型
            )
    })
    public void receive2(String message) {
        System.out.println("message2 = " + message);
    }
}
