package com.bin.controller;

import com.bin.config.DelayedQueueConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 发送延迟消息
 */
@Slf4j
@RestController
@RequestMapping("/ttl")
public class SendMsgController {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public SendMsgController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    //Send message
    @GetMapping("/sendMsg/{message}")
    public void sendMsg(@PathVariable String message){
        log.info("Current time:{},send a message to two TTL queue:{}",new Date().toString(),message);

        rabbitTemplate.convertAndSend("X","XA","message from ttl queue of 10s:" + message);
        rabbitTemplate.convertAndSend("X","XB","message from ttl queue of 40s:" + message);
    }

    @GetMapping("/sendExpirationMsg/{message}/{ttlTime}")
    public void sendMsg(@PathVariable String message,@PathVariable String ttlTime){
        log.info("当前时间为：{},发送一条时长{}毫秒的消息：{}",new Date().toString(),ttlTime,message);
        rabbitTemplate.convertAndSend("X","XC",message,msg -> {
            msg.getMessageProperties().setExpiration(ttlTime);
            return msg;
        });
    }

    //开始发送消息,基于插件的
    @GetMapping("/sendDelayMsg/{message}/{delayTime}")
    public void sendMsg(@PathVariable String message,@PathVariable Integer delayTime){
        log.info("当前时间为：{},发送一条时长{}毫秒的消息delay.queue : {}",new Date().toString(),delayTime,message);
        rabbitTemplate.convertAndSend(DelayedQueueConfig.DELAYED_EXCHANGE_NAME
                ,DelayedQueueConfig.DELAYED_ROUTING_KEY,message,msg ->{
                    //单位是ms
                    msg.getMessageProperties().setDelay(delayTime);
                    return msg;
                });
    }

}
