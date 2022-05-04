package com.ecabs.consumer.service.impl;

import com.ecabs.common.model.Greeting;
import com.ecabs.consumer.service.GreetingListenerService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class GreetingListenerServiceImpl implements GreetingListenerService {

    @Override
    @KafkaListener(topics = "${greeting.topic.name}", containerFactory = "greetingKafkaListenerContainerFactory")
    public void greetingListener(Greeting greeting) {
        System.out.println("Received greeting message: " + greeting);

    }
}
