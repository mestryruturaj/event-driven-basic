package io.enscrypting.bytes.stock_service.service;

import io.enscrypting.bytes.stock_service.dto.ItemDto;
import io.enscrypting.bytes.stock_service.entity.Item;
import io.enscrypting.bytes.stock_service.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItemService {
    private final ItemRepository itemRepository;

    public void addItem(ItemDto itemDto) {

        Item item = Item.builder()
                .name(itemDto.getName())
                .price(itemDto.getPrice())
                .qty(itemDto.getQty())
                .build();
        itemRepository.save(item);
    }

    public void updateItem(ItemDto itemDto) {
        Item curItem = itemRepository.findById(itemDto.getName()).orElse(null);
        if (curItem == null) {
            return;
        }

        curItem.setQty(curItem.getQty() + itemDto.getQty());
        itemRepository.save(curItem);
    }
}
