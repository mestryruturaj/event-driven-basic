package io.enscrypting.bytes.order_service.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    @Value("${kafka.topic.name:demo-topic}")
    private String topicName;

    @Bean
    public NewTopic loadNewTopic() {
        return TopicBuilder
                .name(topicName)
                .build();
    }
}
