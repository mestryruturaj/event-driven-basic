package io.enscrypting.bytes.order_service.service;


import io.enscrypting.bytes.library.dto.Order;
import io.enscrypting.bytes.library.dto.OrderEvent;
import io.enscrypting.bytes.library.enums.EventType;
import io.enscrypting.bytes.order_service.service.kafka.OrderEventPublisherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderEventPublisherService orderEventPublisherService;

    public String placeOrder(Order order) {
        log.info("Order placing initiated for order = {}.", order);

        order.setOrderId(UUID.randomUUID().toString());

        OrderEvent orderEvent = OrderEvent.builder()
                .message("Order status is in pending state.")
                .eventType(EventType.ORDER_PLACED)
                .order(order)
                .build();

        orderEventPublisherService.sendMessage(orderEvent);
        return "Order Placed";
    }

}
