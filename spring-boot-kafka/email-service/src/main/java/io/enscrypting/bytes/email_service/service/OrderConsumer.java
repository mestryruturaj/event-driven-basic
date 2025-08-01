package io.enscrypting.bytes.email_service.service;

import io.enscrypting.bytes.library.dto.OrderEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderConsumer {
    private final EmailService emailService;

    @KafkaListener(topics = "${kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "loadConcurrentKafkaListenerContainerFactory")
    public void consumeOrderEvent(OrderEvent orderEvent) {
        log.info("Order event received in stock service => {}", orderEvent);
        emailService.sendEmail(orderEvent, "mestryruturaj@gmail.com", "mestryruturaj@gmail.com");
    }
}
