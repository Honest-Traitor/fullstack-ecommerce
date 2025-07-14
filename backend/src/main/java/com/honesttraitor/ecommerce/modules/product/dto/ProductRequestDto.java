package com.honesttraitor.ecommerce.modules.product.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class ProductRequestDto {
    @NotBlank(message = "Product name cannot be blank")
    @Size(min = 2, max = 255, message = "Product name must be between 2 and 255 characters")
    private String name;

    @NotBlank(message = "Product description cannot be blank")
    @Size(min = 10, max = 1000, message = "Product description must be between 10 and 1000 characters")
    private String description;

    @NotNull(message = "Price cannot be null")
    @DecimalMin(value = "0.01", message = "Price must be greater than 0") // Price must be positive
    private Double  price;

    @Min(value = 0, message = "Stock quantity cannot be negative") // Stocsk can be 0 or positive
    private int stock;

    public ProductRequestDto() {}

    public ProductRequestDto(String name, String description, double price, int stock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
