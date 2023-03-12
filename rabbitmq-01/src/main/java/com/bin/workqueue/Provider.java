package com.bin.workqueue;

import com.bin.utils.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;

public class Provider {
    public static void main(String[] args) throws IOException {
        //获取连接对象
        Connection connection = RabbitMQUtils.getConnection();
        //获取通道
        Channel channel = connection.createChannel();
        //通过通道生成队列
        channel.queueDeclare("work",true,false,false,null);

        for (int i = 1;i <= 20;i++){
            //发布消息
            channel.basicPublish("","work", null,(i + ":hello work queue").getBytes());
        }


        //关闭资源
        RabbitMQUtils.closeConnectionAndChanel(channel,connection);
    }
}
