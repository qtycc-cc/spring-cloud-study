package com.example.model.order;

import java.math.BigDecimal;
import java.util.List;

import com.example.model.product.Product;

public class Order {
    private Long id;
    private BigDecimal totalPrice;
    private Long userId;
    private List<Product> products;
    public Order() {
    }
    public Order(Long id, BigDecimal totalPrice, Long userId, List<Product> products) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.userId = userId;
        this.products = products;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public List<Product> getProducts() {
        return products;
    }
    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
