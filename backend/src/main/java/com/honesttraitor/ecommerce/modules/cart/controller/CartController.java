package com.honesttraitor.ecommerce.modules.cart.controller;

import com.honesttraitor.ecommerce.modules.cart.dto.CartItemRequestDto;
import com.honesttraitor.ecommerce.modules.cart.dto.CartResponseDto;
import com.honesttraitor.ecommerce.modules.cart.service.CartService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public CartResponseDto getCart() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return cartService.viewCart(email);
    }

    @PostMapping("/add")
    public void addOrUpdate(@RequestBody CartItemRequestDto dto) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        cartService.addOrUpdateItem(email, dto);
    }

    @DeleteMapping("/clear")
    public void clear() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        cartService.clearCart(email);
    }
}

