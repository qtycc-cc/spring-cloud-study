package com.example.order.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.model.order.Order;
import com.example.order.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/create")
    public Order create(@RequestParam("productId") Long productId,
                        @RequestParam("userId") Long userId) {
        return orderService.createOrder(productId, userId);
    }
}
