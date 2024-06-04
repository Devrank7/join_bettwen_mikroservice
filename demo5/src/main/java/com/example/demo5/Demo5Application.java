package com.example.demo5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Demo5Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo5Application.class, args);
    }

}
