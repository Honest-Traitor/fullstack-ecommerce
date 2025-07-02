package com.honesttraitor.ecommerce.modules.order.dto;

import java.time.LocalDateTime;
import java.util.List;

public class OrderResponseDto {
    private Long orderId;
    private LocalDateTime placedAt;
    private List<OrderItemDto> items;
    private double totalAmount;

    public OrderResponseDto() {}

    public OrderResponseDto(Long orderId, LocalDateTime placedAt, List<OrderItemDto> items, double totalAmount) {
        this.orderId = orderId;
        this.placedAt = placedAt;
        this.items = items;
        this.totalAmount = totalAmount;
    }

    // Getters and setters...

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public LocalDateTime getPlacedAt() {
        return placedAt;
    }

    public void setPlacedAt(LocalDateTime placedAt) {
        this.placedAt = placedAt;
    }

    public List<OrderItemDto> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDto> items) {
        this.items = items;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
