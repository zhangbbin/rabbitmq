package com.bin.direct;

import com.bin.utils.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

public class Provider {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        //声明通道交换机 参数1：交换机名称 参数2：direct 路由模式
        channel.exchangeDeclare("logs_direct","direct");
        //发送消息
        String routingkey = "error";
        channel.basicPublish("logs_direct",routingkey,null,("这是direct模型发布的基于routingkey：" + routingkey).getBytes());

        RabbitMQUtils.closeConnectionAndChanel(channel,connection);
    }
}
