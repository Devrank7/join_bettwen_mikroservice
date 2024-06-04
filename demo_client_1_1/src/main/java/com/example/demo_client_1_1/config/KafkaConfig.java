package com.example.demo_client_1_1.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic newTopic() {
        return new NewTopic("demo_2", 1, (short) 1);
    }
}
