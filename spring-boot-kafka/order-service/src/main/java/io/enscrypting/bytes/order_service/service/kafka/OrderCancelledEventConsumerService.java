package io.enscrypting.bytes.order_service.service.kafka;

import io.enscrypting.bytes.library.dto.OrderEvent;
import io.enscrypting.bytes.library.enums.EventType;
import io.enscrypting.bytes.order_service.entity.OrderLog;
import io.enscrypting.bytes.order_service.repository.OrderLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static io.enscrypting.bytes.order_service.util.OrderUtil.createOrderLog;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderCancelledEventConsumerService {
    private final OrderLogRepository orderLogRepository;

    @KafkaListener(
            topics = "${kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "loadConcurrentKafkaListenerContainerFactory"
    )
    public void consumeOrderCancelledEvent(OrderEvent orderEvent) {
        if (orderEvent.getEventType() != EventType.ORDER_CANCELLED) {
            return;
        }
        log.info("Order Cancelled => {}", orderEvent);
        OrderLog orderLog = createOrderLog(orderEvent.getOrder(), orderEvent.getEventType());
        orderLogRepository.save(orderLog);
    }


}
