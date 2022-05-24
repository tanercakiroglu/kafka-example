package com.ecabs.producer.service.impl;

import com.ecabs.common.model.Greeting;
import com.ecabs.producer.service.GreetingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import model.Customer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GreetingServiceImpl implements GreetingService {

    private final KafkaTemplate<String, Greeting> greetingKafkaTemplate;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Value(value = "${greeting.topic.name}")
    private String greetingTopicName;

    @Value(value = "${customer.topic.name}")
    private String customerTopicName;

    @Override
    public List<Greeting> getAll() throws JsonProcessingException {
        var greetingOne = new Greeting("Hello!", "EveryOne");
        var greetingTwo = new Greeting("Hello!", "World");
        sendMessage(greetingOne);
        sendMessage(greetingTwo);
        var customer = Customer.builder().customerId(1L).status("ACTIVE").build();
        sendMessage(customer);

        return List.of(greetingOne, greetingTwo);
    }

    private void sendMessage(Customer customer) throws JsonProcessingException {

        var payload = objectMapper.writeValueAsString(customer);
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(customerTopicName, payload);
        future.addCallback(new ListenableFutureCallback<>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
                System.out.println("Sent message=[" + payload + "] with offset=[" + result.getRecordMetadata()
                        .offset() + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message=[" + payload + "] due to : " + ex.getMessage());
            }
        });
    }

    public void sendMessage(Greeting message) {

        ListenableFuture<SendResult<String, Greeting>> future = greetingKafkaTemplate.send(greetingTopicName, message);

        future.addCallback(new ListenableFutureCallback<>() {

            @Override
            public void onSuccess(SendResult<String, Greeting> result) {
                System.out.println("Sent message=[" + message + "] with offset=[" + result.getRecordMetadata()
                        .offset() + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message=[" + message + "] due to : " + ex.getMessage());
            }
        });
    }
}
