package com.bin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RabbitMQ02Application {
    public static void main(String[] args) {
        SpringApplication.run(RabbitMQ02Application.class,args);
        System.out.println("启动成功！");
    }
}
