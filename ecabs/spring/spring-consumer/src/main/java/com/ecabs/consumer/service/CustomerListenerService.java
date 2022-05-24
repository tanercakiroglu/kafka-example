package com.ecabs.consumer.service;

import com.ecabs.consumer.model.Customer;
import org.springframework.kafka.support.converter.KafkaMessageHeaders;
import org.springframework.messaging.handler.annotation.Payload;

public interface CustomerListenerService {

     void customerListener(@Payload Customer customer,  KafkaMessageHeaders topic);
}
