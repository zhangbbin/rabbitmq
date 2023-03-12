package com.bin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RabbitMQ03Application {
    public static void main(String[] args) {
        SpringApplication.run(RabbitMQ03Application.class,args);
        System.out.println("OK!");
    }
}
