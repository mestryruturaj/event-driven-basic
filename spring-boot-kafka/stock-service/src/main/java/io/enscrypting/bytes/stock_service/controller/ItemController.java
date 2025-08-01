package io.enscrypting.bytes.stock_service.controller;

import io.enscrypting.bytes.stock_service.dto.ItemDto;
import io.enscrypting.bytes.stock_service.entity.Item;
import io.enscrypting.bytes.stock_service.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ItemController {
    private final ItemService itemService;

    @PostMapping("/item")
    public void addItem(@RequestBody ItemDto item) {
        log.info("adding item => {}", item);
        itemService.addItem(item);
    }

    @PutMapping("/item")
    public void updateItem(@RequestBody ItemDto item) {
        log.info("updating item => {}", item);
        itemService.updateItem(item);
    }
}
