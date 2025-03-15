package com.example.order.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.model.product.Product;

// 声明式请求
// 自动负载均衡
// 如果设置了url，表示的是baseURL
@FeignClient("service-product")
public interface OrderFeignService {
    // 发起 GET http://service-product/product/1
    @GetMapping("/product/{productId}")
    Product getProductById(@PathVariable Long productId);
}
