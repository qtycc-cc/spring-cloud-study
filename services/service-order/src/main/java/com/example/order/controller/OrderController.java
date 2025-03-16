package com.example.order.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.model.order.Order;
import com.example.order.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RefreshScope // 自动热刷新
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;
    // 从配置中心读取
    @Value("${order.timeout}")
    String orderTimeout;
    @Value("${order.auto-confirm}")
    String orderAutoConfirm;

    @GetMapping("/create")
    public Order create(@RequestParam("productId") Long productId,
                        @RequestParam("userId") Long userId) {
        return orderService.createOrder(productId, userId);
    }

    @GetMapping("/seckill")
    public Order seckill(@RequestParam("productId") Long productId,
                        @RequestParam("userId") Long userId) {
        return orderService.createOrder(productId, userId);
    }

    @GetMapping("/config")
    public String getConfig() {
        return "orderTimeout: " + orderTimeout + ", " + "orderAutoConfirm: " + orderAutoConfirm;
    }

    @GetMapping("/read")
    public String read() {
        return "reading...";
    }

    @GetMapping("/write")
    public String write() {
        return "writing...";
    }
}
