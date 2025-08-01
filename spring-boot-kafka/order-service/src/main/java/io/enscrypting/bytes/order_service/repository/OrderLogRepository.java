package io.enscrypting.bytes.order_service.repository;

import io.enscrypting.bytes.order_service.entity.OrderLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLogRepository extends JpaRepository<OrderLog, String> {
}
