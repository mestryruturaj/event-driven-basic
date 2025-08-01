package io.enscrypting.bytes.stock_service.service.kafka;


import io.enscrypting.bytes.library.dto.Order;
import io.enscrypting.bytes.library.dto.OrderEvent;
import io.enscrypting.bytes.library.enums.EventType;
import io.enscrypting.bytes.stock_service.entity.Item;
import io.enscrypting.bytes.stock_service.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderEventConsumer {
    private final ItemRepository itemRepository;
    private final OrderEventProducer orderEventProducer;

    @KafkaListener(topics = "${kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "loadConcurrentKafkaListenerContainerFactory")
    public void consumeOrderEvent(OrderEvent orderEvent) {
        if (orderEvent.getEventType() != EventType.ORDER_PLACED) {
            return;
        }

        log.info("Order event received in stock service => {}", orderEvent);

        Order order = orderEvent.getOrder();
        Item item = itemRepository.findById(order.getName()).orElse(null);
        //cancel order
        if (item == null || item.getQty() < order.getQty()) {
            cancelOrder(order);
        }
        //confirm order
        else {
            confirmOrder(order, item);
        }
    }

    private void confirmOrder(Order order, Item item) {
        log.info("order confirmed => {}", order);
        //reduce from item stock and save
        item.setQty(item.getQty() - order.getQty());
        itemRepository.save(item);

        //public order confirmed event
        orderEventProducer.publishEvent(order, EventType.ORDER_CONFIRMED, "Order has been cancelled.");
    }

    private void cancelOrder(Order order) {
        log.info("order cancelled => {}", order);

        //public order cancelled event
        orderEventProducer.publishEvent(order, EventType.ORDER_CANCELLED, "Order has been cancelled.");
    }
}
