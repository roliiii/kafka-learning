package com.fluffy.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public final class ConsumerService {
    private static final Logger logger = LoggerFactory.getLogger(ConsumerService.class);

    @KafkaListener(topics = "fluffyTopic", groupId = "fluffy_group")
    public void consume(String message) {
        logger.info(String.format("Message received: %s", message));
    }
}
