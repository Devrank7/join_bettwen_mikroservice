package com.example.demo_client_1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class DemoClient1Application {

    public static void main(String[] args) {
        SpringApplication.run(DemoClient1Application.class, args);
    }

}
