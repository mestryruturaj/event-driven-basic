package io.enscrypting.bytes.library.dto;


import io.enscrypting.bytes.library.enums.EventType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderEvent {
    private String message;
    private EventType eventType;
    private Order order;
}
