package com.example.order.service.impl;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.model.product.Product;
import com.example.order.service.OrderFeignService;

/**
 * 服务熔断回调
 */
@Component
public class OrderFeignServiceFallback implements OrderFeignService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Product getProductById(Long productId) { // 返回默认数据
        logger.info("Fall back!!");
        Product product = new Product();
        product.setId(productId);
        product.setPrice(new BigDecimal("0"));
        product.setName("未知商品");
        product.setNum(0L);

        return product;
    }
}
