package com.example.order.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.example.model.order.Order;
import com.example.model.product.Product;
import com.example.order.service.OrderFeignService;
import com.example.order.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
    private Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    // @Autowired
    // private DiscoveryClient discoveryClient;
    // @Autowired
    // private LoadBalancerClient loadBalancerClient;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private OrderFeignService orderFeignService;

    @SentinelResource(value = "createOrder", blockHandler = "createOrderFallback")
    @Override
    public Order createOrder(Long productId, Long userId) {
        // try {
        //     SphU.entry(""); // 编程式处理规则
        // } catch (BlockException e) {

        // }
        Order order = new Order();
        // Product product = getProductFromRemoteWithBalance(productId);
        Product product = orderFeignService.getProductById(productId);
        order.setId(1L);
        order.setUserId(userId);
        order.setTotalPrice(product.getPrice().multiply(new BigDecimal(product.getNum())));
        order.setProducts(new ArrayList<>() {{ add(product); }});
        return order;
    }

    /**
     * 专用于@SentinelResource类型资源的fallback
     * @param productId
     * @param userId
     * @param e
     * @return
     */
    public Order createOrderFallback(Long productId, Long userId, BlockException e) {
        logger.info("Fall back!");
        Order order = new Order();
        order.setId(0L);
        order.setTotalPrice(new BigDecimal("0"));
        order.setUserId(userId);

        return order;
    }

    private Product getProductFromRemoteWithBalance(Long productId) {
        // 发现服务
        // List<ServiceInstance> productServiceInstances = discoveryClient.getInstances("service-product");
        // ServiceInstance productServiceInstance = productServiceInstances.get(0);

        // ServiceInstance choose = loadBalancerClient.choose("service-product");
        // String requestUrl = "http://" + choose.getHost() + ":" + choose.getPort()
        //         + "/product/" + productId;

        // dynamic
        String requestUrl = "http://service-product/product/"+ productId;

        logger.info("Request remote service-product, requesturl is: {}", requestUrl);
        return restTemplate.getForObject(requestUrl, Product.class);
    }
}
