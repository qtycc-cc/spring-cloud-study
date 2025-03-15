package com.example.product.service.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.example.model.product.Product;
import com.example.product.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public Product getProductById(Long id) {
        return new Product(id, new BigDecimal(10), "IPhone 12", 2L);
    }
}
