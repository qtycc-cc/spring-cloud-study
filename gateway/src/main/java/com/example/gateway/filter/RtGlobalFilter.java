package com.example.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
// import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

/**
 * 全局过滤器
 */
@Component
public class RtGlobalFilter implements GlobalFilter, Ordered {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        // ServerHttpResponse response = exchange.getResponse();
        log.info("Request {} start: time: {}",
                request.getURI().toString(), System.currentTimeMillis());
        // =======以上是前置操作========
        Mono<Void> filter = chain.filter(exchange)
                            .doFinally(result -> {
                                // =======以下是后置操作========
                                log.info("Request {} start: time: {}",
                request.getURI().toString(), System.currentTimeMillis());
                            });
        return filter;
    }
}
