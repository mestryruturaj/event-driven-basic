package io.enscrypting.bytes.order_service.service.kafka;


import io.enscrypting.bytes.library.dto.OrderEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderEventPublisherService {
    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;
    private final NewTopic orderTopic;

    public void sendMessage(OrderEvent orderEvent) {
        log.info("OrderEvent => {}", orderEvent);

        Message<OrderEvent> message = MessageBuilder
                .withPayload(orderEvent)
                .setHeaderIfAbsent(KafkaHeaders.TOPIC, orderTopic.name())
                .build();

        kafkaTemplate.send(message);
    }
}
