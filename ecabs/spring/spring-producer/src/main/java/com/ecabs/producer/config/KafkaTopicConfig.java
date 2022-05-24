package com.ecabs.producer.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Value(value = "${greeting.topic.name}")
    private String greetingTopicName;

    @Value(value = "${customer.topic.name}")
    private String customerTopicName;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        configs.put(AdminClientConfig.RECONNECT_BACKOFF_MAX_MS_CONFIG, 2000);
        configs.put(AdminClientConfig.RECONNECT_BACKOFF_MS_CONFIG, 2000);
        configs.put(AdminClientConfig.RETRIES_CONFIG, 0);
        configs.put(AdminClientConfig.RETRY_BACKOFF_MS_CONFIG, 2000);
        configs.put(AdminClientConfig.REQUEST_TIMEOUT_MS_CONFIG, 500);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic greetingTopic() {
        return TopicBuilder.name(greetingTopicName)
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic consumerTopic() {
        return TopicBuilder.name(customerTopicName)
                .partitions(1)
                .replicas(1)
                .build();
    }
}
