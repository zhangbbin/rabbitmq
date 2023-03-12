package com.bin.workqueue;

import com.bin.utils.RabbitMQUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

public class Consumer1 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.basicQos(1);//每一次只能消费一次消息
        channel.queueDeclare("work",true,false,false,null);
        //参数1：队列名称 参数2:消息自动确认 true:消费者自动向rabbitmq确认消息消费 false:不会自动确认消息
        channel.basicConsume("work",false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者-1=====>" + new String(body));
            }
        });

    }
}
