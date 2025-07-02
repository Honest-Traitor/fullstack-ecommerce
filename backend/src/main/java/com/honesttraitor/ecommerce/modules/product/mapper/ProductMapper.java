package com.honesttraitor.ecommerce.modules.product.mapper;

import com.honesttraitor.ecommerce.modules.product.dto.ProductRequestDto;
import com.honesttraitor.ecommerce.modules.product.dto.ProductResponseDto;
import com.honesttraitor.ecommerce.modules.product.model.Product;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toEntity(ProductRequestDto dto);

    ProductResponseDto toDto(Product product);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(ProductRequestDto dto, @MappingTarget Product product);
}
