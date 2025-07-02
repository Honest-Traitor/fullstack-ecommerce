package com.honesttraitor.ecommerce.modules.cart.dto;

import java.util.List;

public class CartResponseDto {
    private Long cartId;
    private List<CartItemViewDto> items;
    private double cartTotal;

    // Constructors, getters, setters...

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public List<CartItemViewDto> getItems() {
        return items;
    }

    public void setItems(List<CartItemViewDto> items) {
        this.items = items;
    }

    public double getCartTotal() {
        return cartTotal;
    }

    public void setCartTotal(double cartTotal) {
        this.cartTotal = cartTotal;
    }

    public CartResponseDto(Long cartId, List<CartItemViewDto> items, double cartTotal) {
        this.cartId = cartId;
        this.items = items;
        this.cartTotal = cartTotal;
    }

    public CartResponseDto() {
    }
}