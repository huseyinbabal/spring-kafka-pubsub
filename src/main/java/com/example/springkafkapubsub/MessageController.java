package com.example.springkafkapubsub;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.mediatype.MessageResolver;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageRepository messageRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping()
    public void create(@RequestBody CreateMessageRequest request) {
        Message msg = this.messageRepository.save(Message.builder().message(request.getMessage()).build());
        this.kafkaTemplate.send("messages", msg.getMessage());
    }

    @Data
    public static class CreateMessageRequest {
        private String message;
    }

}
