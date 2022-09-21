package com.project.usermodule.kafka;


import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class UserProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserProducer.class);


    private NewTopic newTopic;
    private KafkaTemplate<String, UserDto> kafkaTemplate;

    public UserProducer(NewTopic newTopic, KafkaTemplate<String, UserDto> kafkaTemplate) {
        this.newTopic = newTopic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(UserDto event) {
        LOGGER.info(String.format("User event => %s", event.toString()));

        // create Message
        Message<UserDto> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, newTopic.name())
                .build();
        kafkaTemplate.send(message);


    }

}
