package com.bin.hello;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component      //持久化 非独占 不是自动删除的队列
@RabbitListener(queuesToDeclare = @Queue(value = "hello"))
public class HelloConsumer {

    //回调方法
    @RabbitHandler
    public void receive(String message){
        System.out.println("message = " + message);
    }

}
