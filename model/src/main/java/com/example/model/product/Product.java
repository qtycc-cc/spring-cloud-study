package com.example.model.product;

import java.math.BigDecimal;

public class Product {
    private Long id;
    private BigDecimal price;
    private String name;
    private Long num;
    public Product() {
    }
    public Product(Long id, BigDecimal price, String name, Long num) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.num = num;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getNum() {
        return num;
    }
    public void setNum(Long num) {
        this.num = num;
    }
}
