package com.bin.topic;

import com.bin.utils.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

public class Provider {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare("topics","topic");

        String routekey = "save.user.save";

        channel.basicPublish("topics",routekey,null,("这是topic动态路由模型,routekey:" + routekey).getBytes());

        RabbitMQUtils.closeConnectionAndChanel(channel,connection);
    }
}
