package io.enscrypting.bytes.stock_service.service.kafka;

import io.enscrypting.bytes.library.dto.Order;
import io.enscrypting.bytes.library.dto.OrderEvent;
import io.enscrypting.bytes.library.enums.EventType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderEventProducer {
    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    @Value("${kafka.topic.name}")
    private String topicName;

    public void publishEvent(Order order, EventType eventType, String msg) {
        OrderEvent orderEvent = OrderEvent.builder()
                .eventType(eventType)
                .order(order)
                .message(msg)
                .build();

        Message<OrderEvent> message = MessageBuilder
                .withPayload(orderEvent)
                .setHeaderIfAbsent(KafkaHeaders.TOPIC, topicName)
                .build();

        kafkaTemplate.send(message);
    }
}
