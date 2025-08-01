package io.enscrypting.bytes.order_service.util;

import io.enscrypting.bytes.library.dto.Order;
import io.enscrypting.bytes.library.dto.OrderEvent;
import io.enscrypting.bytes.library.enums.EventType;
import io.enscrypting.bytes.order_service.entity.OrderLog;

public class OrderUtil {
    public static OrderEvent createOrderEvent(Order order) {
        return OrderEvent.builder()
                .message("Order status is in pending state.")
                .eventType(EventType.ORDER_PLACED)
                .order(order)
                .build();
    }

    public static OrderLog createOrderLog(Order order, EventType eventType) {
        return OrderLog.builder()
                .orderId(order.getOrderId())
                .eventType(eventType.name())
                .item(order.getName())
                .qty(order.getQty())
                .price(order.getPrice())
                .build();
    }
}
