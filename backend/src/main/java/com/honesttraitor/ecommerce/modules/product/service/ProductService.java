package com.honesttraitor.ecommerce.modules.product.service;

import com.honesttraitor.ecommerce.modules.product.dto.ProductRequestDto;
import com.honesttraitor.ecommerce.modules.product.dto.ProductResponseDto;

import java.util.List;

public interface ProductService {
    List<ProductResponseDto> getAll();
    ProductResponseDto getById(Long id);
    ProductResponseDto create(ProductRequestDto dto);
    ProductResponseDto update(Long id, ProductRequestDto dto);
    void delete(Long id);
}
