package com.honesttraitor.ecommerce.modules.cart.dto;

import java.util.List;

public class CartItemViewDto {
    private Long productId;
    private String productName;
    private double price;
    private int quantity;
    private double total;

    // Constructors, getters, setters...

    public CartItemViewDto(Long productId, String productName, double price, int quantity, double total) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
    }

    public CartItemViewDto() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}

