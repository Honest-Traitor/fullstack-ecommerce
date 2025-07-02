package com.honesttraitor.ecommerce.modules.cart.mapper;

import com.honesttraitor.ecommerce.modules.cart.dto.CartItemViewDto;
import com.honesttraitor.ecommerce.modules.cart.dto.CartResponseDto;
import com.honesttraitor.ecommerce.modules.cart.model.Cart;
import com.honesttraitor.ecommerce.modules.cart.model.CartItem;
import com.honesttraitor.ecommerce.modules.product.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CartMapper {

    public CartResponseDto toDto(Cart cart, List<CartItem> items, Map<Long, Product> productsById) {
        List<CartItemViewDto> itemViews = items.stream().map(item -> {
            Product product = productsById.get(item.getProductId());
            double total = product.getPrice() * item.getQuantity();

            return new CartItemViewDto(
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    item.getQuantity(),
                    total
            );
        }).collect(Collectors.toList());

        double cartTotal = itemViews.stream().mapToDouble(CartItemViewDto::getTotal).sum();
        return new CartResponseDto(cart.getId(), itemViews, cartTotal);
    }
}

