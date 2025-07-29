package io.enscrypting.bytes.order_service.controller;

import io.enscrypting.bytes.library.dto.Order;
import io.enscrypting.bytes.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping(value="/orders")
    public String placeOrder(@RequestBody Order order) {
        return orderService.placeOrder(order);
    }
}
