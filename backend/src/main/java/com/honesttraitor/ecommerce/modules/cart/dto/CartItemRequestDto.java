package com.honesttraitor.ecommerce.modules.cart.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class CartItemRequestDto {
    @NotNull(message = "Product ID cannot be null") // Product ID must be present
    @Min(value = 1, message = "Product ID must be a positive number") // Product ID should be at least 1
    private Long productId;

    @Min(value = 0, message = "Quantity must be a non-negative number") // Quantity can be 0 (for removal) or positive
    private int quantity;

    public CartItemRequestDto() {}

    public CartItemRequestDto(Long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    // Getters and setters...

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

