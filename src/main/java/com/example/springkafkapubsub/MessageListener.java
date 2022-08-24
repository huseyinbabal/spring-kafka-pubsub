package com.example.springkafkapubsub;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.KafkaListeners;

@EnableKafka
@Slf4j
public class MessageListener {

    @KafkaListener(topics = "messages", groupId = "minikube")
    public void listen(String message) {
        log.info("Message received: {}", message);
    }
}
