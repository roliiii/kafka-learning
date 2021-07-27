package com.fluffy.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafka")
public class ProducerController {

    @Autowired
    private ProducerService producerService;

    @GetMapping(value = "/publish")
    public void sendMessageToKafkaTopic(@RequestParam String message) {
        producerService.sendMessage(message);
    }
}
