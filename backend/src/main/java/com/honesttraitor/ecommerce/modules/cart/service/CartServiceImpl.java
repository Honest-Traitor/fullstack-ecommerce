package com.honesttraitor.ecommerce.modules.cart.service;

import com.honesttraitor.ecommerce.common.exception.ApiException;
import com.honesttraitor.ecommerce.modules.cart.dto.CartItemRequestDto;
import com.honesttraitor.ecommerce.modules.cart.dto.CartResponseDto;
import com.honesttraitor.ecommerce.modules.cart.mapper.CartMapper;
import com.honesttraitor.ecommerce.modules.cart.model.Cart;
import com.honesttraitor.ecommerce.modules.cart.model.CartItem;
import com.honesttraitor.ecommerce.modules.cart.repository.CartItemRepository;
import com.honesttraitor.ecommerce.modules.cart.repository.CartRepository;
import com.honesttraitor.ecommerce.modules.product.model.Product;
import com.honesttraitor.ecommerce.modules.product.repository.ProductRepository;
import com.honesttraitor.ecommerce.modules.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartMapper cartMapper;

    public CartServiceImpl(CartRepository cartRepository,
                           CartItemRepository cartItemRepository,
                           UserRepository userRepository,
                           ProductRepository productRepository,
                           CartMapper cartMapper) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.cartMapper = cartMapper;
    }

    @Override
    public CartResponseDto viewCart(String userEmail) {
        Long userId = getUserId(userEmail);
        Cart cart = cartRepository.findByUserId(userId).orElseGet(() -> cartRepository.save(Cart.builder().userId(userId).build()));
        List<CartItem> items = cartItemRepository.findByCartId(cart.getId());

        Map<Long, Product> productMap = productRepository.findAllById(
                items.stream().map(CartItem::getProductId).collect(Collectors.toSet())
        ).stream().collect(Collectors.toMap(Product::getId, p -> p));

        return cartMapper.toDto(cart, items, productMap);
    }

    @Override
    public void addOrUpdateItem(String userEmail, CartItemRequestDto dto) {
        Long userId = getUserId(userEmail);
        Cart cart = cartRepository.findByUserId(userId).orElseGet(() -> cartRepository.save(Cart.builder().userId(userId).build()));
        CartItem item = cartItemRepository.findByCartIdAndProductId(cart.getId(), dto.getProductId())
                .map(existing -> {
                    existing.setQuantity(dto.getQuantity());
                    return existing;
                }).orElse(CartItem.builder()
                        .cartId(cart.getId())
                        .productId(dto.getProductId())
                        .quantity(dto.getQuantity())
                        .build());

        cartItemRepository.save(item);
    }

    @Override
    @Transactional
    public void clearCart(String userEmail) {
        Long userId = getUserId(userEmail);
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new ApiException("Cart not found"));
        cartItemRepository.deleteByCartId(cart.getId());
    }

    private Long getUserId(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ApiException("User not found"))
                .getId();
    }
}
