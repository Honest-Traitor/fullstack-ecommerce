package com.honesttraitor.ecommerce.modules.order.service;
import com.honesttraitor.ecommerce.modules.order.dto.OrderResponseDto;

import java.util.List;

public interface OrderService {
    void placeOrder(String userEmail);
    List<OrderResponseDto> getUserOrders(String userEmail);
}
