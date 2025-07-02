package com.honesttraitor.ecommerce.modules.order.mapper;

import com.honesttraitor.ecommerce.modules.order.dto.*;
import com.honesttraitor.ecommerce.modules.order.model.*;
import com.honesttraitor.ecommerce.modules.product.model.Product;
import org.mapstruct.*;

import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(source = "id", target = "orderId")
    @Mapping(target = "items", ignore = true) // populated manually
    @Mapping(target = "totalAmount", ignore = true)
    OrderResponseDto toDto(Order order, @Context List<OrderItem> items, @Context Map<Long, Product> productMap);

    @AfterMapping
    default void mapItemsAndTotal(@MappingTarget OrderResponseDto dto,
                                  Order order,
                                  @Context List<OrderItem> items,
                                  @Context Map<Long, Product> productMap) {

        List<OrderItemDto> itemDtos = items.stream().map(item -> {
            Product product = productMap.get(item.getProductId());
            return new OrderItemDto(
                    item.getProductId(),
                    product != null ? product.getName() : "Unknown Product",
                    item.getQuantity(),
                    item.getPriceAtPurchase()
            );
        }).toList();

        double total = itemDtos.stream()
                .mapToDouble(i -> i.getQuantity() * i.getPriceAtPurchase())
                .sum();

        dto.setItems(itemDtos);
        dto.setTotalAmount(total);
    }
}
