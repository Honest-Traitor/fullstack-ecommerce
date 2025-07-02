package com.honesttraitor.ecommerce.modules.order.controller;

import com.honesttraitor.ecommerce.modules.order.dto.OrderResponseDto;
import com.honesttraitor.ecommerce.modules.order.service.OrderService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public void placeOrder() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        orderService.placeOrder(email);
    }

    @GetMapping
    public List<OrderResponseDto> getMyOrders() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return orderService.getUserOrders(email);
    }
}
