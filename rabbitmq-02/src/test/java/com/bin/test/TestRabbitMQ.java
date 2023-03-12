package com.bin.test;


import com.bin.RabbitMQ02Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = RabbitMQ02Application.class)
@RunWith(SpringRunner.class)
public class TestRabbitMQ {

    //注入rabbitTemplate对象
    @Autowired
    private RabbitTemplate rabbitTemplate;


    //topic 动态路由 订阅模式
    @Test
    public void testTopic(){
        rabbitTemplate.convertAndSend("topics","user.save","user.save路由消息");
    }

    //route 路由模式
    @Test
    public void testRoute() {
        rabbitTemplate.convertAndSend("directs", "info", "发送的是info的信息");
    }

    //fanout 广播
    @Test
    public void testFanout() {
        rabbitTemplate.convertAndSend("logs", "", "Fanout模型发送消息");
    }

    //work
    @Test
    public void testWork() {
        rabbitTemplate.convertAndSend("work", "work模型");
    }

    //hello world  生产者 参数1：队列名称  参数2：消息内容
    @Test
    public void testHello() {
        rabbitTemplate.convertAndSend("hello", "hello world");
    }
}
