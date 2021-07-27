package com.fluffy.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class ProducerService {

    private static final Logger logger = LoggerFactory.getLogger(ProducerService.class);

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String TOPIC = "fluffyTopic";

    public ProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    public void sendMessage(String message) {
        logger.info(String.format("Message Sending: %s", message));

        ListenableFuture<SendResult<String, String>> future = this.kafkaTemplate.send(TOPIC, message);
        future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onFailure(Throwable ex) {
                logger.info("Unable to send message=[ {} ] due to : {}", message, ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                logger.info("Sent message=[ {} ] with offset=[ {} ]", message, result.getRecordMetadata().offset());
            }
        });
    }

}
