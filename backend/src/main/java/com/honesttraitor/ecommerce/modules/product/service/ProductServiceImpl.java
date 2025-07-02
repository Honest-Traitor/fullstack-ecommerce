package com.honesttraitor.ecommerce.modules.product.service;

import com.honesttraitor.ecommerce.common.exception.ApiException;
import com.honesttraitor.ecommerce.modules.product.dto.ProductRequestDto;
import com.honesttraitor.ecommerce.modules.product.dto.ProductResponseDto;
import com.honesttraitor.ecommerce.modules.product.mapper.ProductMapper;
import com.honesttraitor.ecommerce.modules.product.model.Product;
import com.honesttraitor.ecommerce.modules.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository,
                              ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public List<ProductResponseDto> getAll() {
        return productRepository.findAll().stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponseDto getById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ApiException("Product not found"));
        return productMapper.toDto(product);
    }

    @Override
    public ProductResponseDto create(ProductRequestDto dto) {
        Product product = productMapper.toEntity(dto);
        return productMapper.toDto(productRepository.save(product));
    }

    @Override
    public ProductResponseDto update(Long id, ProductRequestDto dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ApiException("Product not found"));
        productMapper.updateEntityFromDto(dto, product);
        return productMapper.toDto(productRepository.save(product));
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
