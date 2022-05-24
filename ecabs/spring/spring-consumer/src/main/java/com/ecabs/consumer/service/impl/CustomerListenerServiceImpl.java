package com.ecabs.consumer.service.impl;

import com.ecabs.consumer.model.Customer;
import com.ecabs.consumer.service.CustomerListenerService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.converter.KafkaMessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class CustomerListenerServiceImpl implements CustomerListenerService {

    @Override
    @KafkaListener(topics = "${customer.topic.name}", containerFactory = "kafkaListenerContainerFactory")
    public void customerListener(@Payload Customer customer, @Headers KafkaMessageHeaders topic) {
        System.out.println("Received customer message: " + customer);

    }
}
