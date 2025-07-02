package com.honesttraitor.ecommerce.modules.order.repository;

import com.honesttraitor.ecommerce.modules.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);
}

