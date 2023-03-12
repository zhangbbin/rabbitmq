package com.bin.workqueue;

import com.bin.utils.RabbitMQUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

public class Consumer2 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.basicQos(1);
        channel.queueDeclare("work",true,false,false,null);
        channel.basicConsume("work",false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者-2=====>" + new String(body));
                //手动确认 参数1:手动确认消息标识 参数2:每次确认一个，不开启多消息确认
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });
        
    }
}
