package io.enscrypting.bytes.stock_service.repository;

import io.enscrypting.bytes.stock_service.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, String> {
}
