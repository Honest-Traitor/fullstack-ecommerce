package com.honesttraitor.ecommerce.modules.cart.service;

import com.honesttraitor.ecommerce.modules.cart.dto.CartItemRequestDto;
import com.honesttraitor.ecommerce.modules.cart.dto.CartResponseDto;

public interface CartService {
    CartResponseDto viewCart(String userEmail);
    void addOrUpdateItem(String userEmail, CartItemRequestDto dto);
    void clearCart(String userEmail);
}