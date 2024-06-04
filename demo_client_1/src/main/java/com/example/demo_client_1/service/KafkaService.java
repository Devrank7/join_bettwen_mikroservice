package com.example.demo_client_1.service;

import com.example.demo_client_1.model.Item_Loader;
import com.example.demo_client_1.model.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class KafkaService {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private List<Item_Loader> itemLoader;

    volatile boolean isAnaliz = false;

    public void sendMessage(Person person) {
        log.error("test point");
        kafkaTemplate.send("demo_1", "id_key", Integer.toString(person.getId()));
        log.info("HERE");
    }

    @KafkaListener(topics = "demo_2", groupId = "group_id")
    public void consume(ConsumerRecord<String, String> consumerRecord) throws JsonProcessingException {
        log.warn(" = " + consumerRecord.key());
        if (consumerRecord.key().equals("data")) {
            System.out.println(consumerRecord.value());
        }
        List<Item_Loader> itemLoader = new ObjectMapper().readValue(consumerRecord.value(), new TypeReference<List<Item_Loader>>() {
        });
        for (Item_Loader itemLoader1 : itemLoader) {
            System.out.println("id = " + itemLoader1.getId());
            System.out.println("name = " + itemLoader1.getName());
            System.out.println("price = " + itemLoader1.getPrice());
            System.out.println("=====================================");
        }
        this.itemLoader = itemLoader;
        isAnaliz = true;
        //return itemLoader;
    }


    public List<Item_Loader> getItemLoader(Person person) {
        sendMessage(person);
        System.out.println("id = " + person.getId());
        while (!isAnaliz) {
            Thread.onSpinWait();
        }
        isAnaliz = false;
        return itemLoader;
    }
}
