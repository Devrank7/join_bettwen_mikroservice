package com.example.demo_client_1_1.service;

import com.example.demo_client_1_1.IItem;
import com.example.demo_client_1_1.model.Item;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class KafkaConsumerService {
    private KafkaTemplate<String, String> kafkaTemplate;
    public IItem iItem;

    @KafkaListener(topics = "demo_1", groupId = "group_id")
    public void kafka_listener(ConsumerRecord<String, String> s) {
        log.info("key = " + s.key());
        if (s.key().equals("id_key")) {
            log.info("--------------------------- " + s.value());
            sendData(Integer.parseInt(s.value()));
        }
    }

    public void sendData(int id) {
        List<Item> items = iItem.findAll();
        List<Item> sortList = items.stream().filter(so -> so.getForeign_key_by_user() == id).toList();
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Item item : sortList) {
            if (sb.length() > 3) {
                sb.append(",");
            }
            sb.append("{");
            sb.append("\"id\" ").append(": \"").append(Integer.toString(item.getId())).append("\"");
            sb.append(",");
            sb.append("\"name\" ").append(": \"").append(item.getName()).append("\"");
            sb.append(",");
            sb.append("\"price\" ").append(": \"").append(Integer.toString(item.getPrice())).append("\"");
            sb.append("}");
        }
        sb.append("]");
        log.info("uuuuuuuuuuuuuuuuuu + " + sb.toString());
        kafkaTemplate.send("demo_2", "data", sb.toString());

    }


}
