package com.honesttraitor.ecommerce.modules.order.service;

import com.honesttraitor.ecommerce.common.exception.ApiException;
import com.honesttraitor.ecommerce.modules.cart.model.Cart;
import com.honesttraitor.ecommerce.modules.cart.model.CartItem;
import com.honesttraitor.ecommerce.modules.cart.repository.CartItemRepository;
import com.honesttraitor.ecommerce.modules.cart.repository.CartRepository;
import com.honesttraitor.ecommerce.modules.order.dto.OrderResponseDto;
import com.honesttraitor.ecommerce.modules.order.mapper.OrderMapper;
import com.honesttraitor.ecommerce.modules.order.model.Order;
import com.honesttraitor.ecommerce.modules.order.model.OrderItem;
import com.honesttraitor.ecommerce.modules.order.repository.OrderItemRepository;
import com.honesttraitor.ecommerce.modules.order.repository.OrderRepository;
import com.honesttraitor.ecommerce.modules.product.model.Product;
import com.honesttraitor.ecommerce.modules.product.repository.ProductRepository;
import com.honesttraitor.ecommerce.modules.user.model.User;
import com.honesttraitor.ecommerce.modules.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final OrderMapper orderMapper;

    public OrderServiceImpl(OrderRepository orderRepository,
                            OrderItemRepository orderItemRepository,
                            CartRepository cartRepository,
                            CartItemRepository cartItemRepository,
                            ProductRepository productRepository,
                            UserRepository userRepository,
                            OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    @Transactional
    public void placeOrder(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ApiException("User not found"));

        Cart cart = cartRepository.findByUserId(user.getId())
                .orElseThrow(() -> new ApiException("Cart not found"));

        List<CartItem> cartItems = cartItemRepository.findByCartId(cart.getId());
        if (cartItems.isEmpty()) {
            throw new ApiException("Cart is empty");
        }

        // Fetch product data
        Set<Long> productIds = cartItems.stream()
                .map(CartItem::getProductId)
                .collect(Collectors.toSet());

        Map<Long, Product> productMap = productRepository.findAllById(productIds).stream()
                .collect(Collectors.toMap(Product::getId, p -> p));

        // ✅ Stock validation + in-place adjustment
        for (CartItem ci : cartItems) {
            Product product = productMap.get(ci.getProductId());
            if (product == null) {
                throw new ApiException("Product not found: ID " + ci.getProductId());
            }
            if (product.getStock() < ci.getQuantity()) {
                throw new ApiException("Insufficient stock for product: " + product.getName());
            }
            product.setStock(product.getStock() - ci.getQuantity());
        }

        // 💾 Persist updated stock before creating order
        productRepository.saveAll(productMap.values());

        // 🧾 Create and save the order
        Order order = orderRepository.save(Order.builder()
                .userId(user.getId())
                .placedAt(LocalDateTime.now())
                .build());

        // 🧾 Create and save OrderItems
        List<OrderItem> itemsToSave = cartItems.stream().map(ci -> {
            Product product = productMap.get(ci.getProductId());
            return OrderItem.builder()
                    .orderId(order.getId())
                    .productId(ci.getProductId())
                    .quantity(ci.getQuantity())
                    .priceAtPurchase(product.getPrice())
                    .build();
        }).toList();

        orderItemRepository.saveAll(itemsToSave);

        // 🧹 Clear cart
        cartItemRepository.deleteByCartId(cart.getId());
    }


    @Override
    public List<OrderResponseDto> getUserOrders(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ApiException("User not found"));

        List<Order> orders = orderRepository.findByUserId(user.getId());

        return orders.stream().map(order -> {
            List<OrderItem> items = orderItemRepository.findByOrderId(order.getId());
            Set<Long> productIds = items.stream().map(OrderItem::getProductId).collect(Collectors.toSet());
            Map<Long, Product> products = productRepository.findAllById(productIds).stream()
                    .collect(Collectors.toMap(Product::getId, p -> p));
            return orderMapper.toDto(order, items, products);
        }).toList();
    }
}
