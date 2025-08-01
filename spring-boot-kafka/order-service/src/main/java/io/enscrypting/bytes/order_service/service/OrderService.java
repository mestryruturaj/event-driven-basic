package io.enscrypting.bytes.order_service.service;


import io.enscrypting.bytes.library.dto.Order;
import io.enscrypting.bytes.library.dto.OrderEvent;
import io.enscrypting.bytes.library.enums.EventType;
import io.enscrypting.bytes.order_service.entity.OrderLog;
import io.enscrypting.bytes.order_service.repository.OrderLogRepository;
import io.enscrypting.bytes.order_service.service.kafka.OrderPlacedEventPublisherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static io.enscrypting.bytes.order_service.util.OrderUtil.createOrderEvent;
import static io.enscrypting.bytes.order_service.util.OrderUtil.createOrderLog;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderPlacedEventPublisherService orderPlacedEventPublisherService;
    private final OrderLogRepository orderLogRepository;


    //place order flow
    public String placeOrder(Order order) {
        log.info("Order placing initiated for order = {}.", order);

        order.setOrderId(UUID.randomUUID().toString());

        OrderLog orderLog = createOrderLog(order, EventType.ORDER_PLACED);
        orderLogRepository.save(orderLog);

        OrderEvent orderEvent = createOrderEvent(order);
        orderPlacedEventPublisherService.sendMessage(orderEvent);
        return "Order Placed";
    }



    //cancel order flow

}
