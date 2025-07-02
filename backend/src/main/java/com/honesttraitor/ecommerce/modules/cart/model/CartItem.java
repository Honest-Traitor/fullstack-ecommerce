package com.honesttraitor.ecommerce.modules.cart.model;
import jakarta.persistence.*;

@Entity
@Table(name = "cart_items")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long cartId;
    private Long productId;
    private int quantity;

    public CartItem() {}

    public CartItem(Long id, Long cartId, Long productId, int quantity) {
        this.id = id;
        this.cartId = cartId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private Long cartId;
        private Long productId;
        private int quantity;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder cartId(Long cartId) { this.cartId = cartId; return this; }
        public Builder productId(Long productId) { this.productId = productId; return this; }
        public Builder quantity(int quantity) { this.quantity = quantity; return this; }

        public CartItem build() {
            return new CartItem(id, cartId, productId, quantity);
        }
    }

    // Getters and setters...

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

