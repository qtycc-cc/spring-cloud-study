package com.example.product.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.model.product.Product;
import com.example.product.service.ProductService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class ProductController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ProductService productService;
    @GetMapping("/product/{productId}")
    public Product getProduct(@PathVariable Long productId) {
        logger.info("I have been requested!");
        return productService.getProductById(productId);
    }
}
