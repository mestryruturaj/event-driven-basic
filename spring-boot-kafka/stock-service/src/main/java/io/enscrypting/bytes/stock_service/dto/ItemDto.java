package io.enscrypting.bytes.stock_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
    private String name;
    private double price;
    private int qty;
}
