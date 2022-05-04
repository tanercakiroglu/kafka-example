package com.ecabs.producer.service.impl;

import com.ecabs.common.model.Greeting;
import com.ecabs.producer.service.GreetingService;
import lombok.RequiredArgsConstructor;
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

    @Value(value = "${greeting.topic.name}")
    private String greetingTopicName;

    @Override
    public List<Greeting> getAll() {
        var greetingOne  = new Greeting("Hello!","EveryOne");
        var greetingTwo  = new Greeting("Hello!","World");
        sendMessage(greetingOne);
        sendMessage(greetingTwo);
        return List.of(greetingOne,greetingTwo);
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
