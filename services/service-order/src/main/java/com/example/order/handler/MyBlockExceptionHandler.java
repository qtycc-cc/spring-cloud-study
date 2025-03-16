package com.example.order.handler;

import java.io.PrintWriter;

import org.springframework.stereotype.Component;

import com.alibaba.csp.sentinel.adapter.spring.webmvc_v6x.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.example.model.common.R;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 自定义Web接口异常处理
 * 用于捕获@RequestMapping之类的方法规则异常
 */
@Component
public class MyBlockExceptionHandler implements BlockExceptionHandler {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            String resourceName, BlockException e) throws Exception {
        response.setStatus(429); // too many requests
        response.setContentType("application/json;charset=utf-8");

        PrintWriter writer = response.getWriter();

        R error = R.error(resourceName + " 被Sentinel限制了, 原因: " + e.getClass());

        String json = objectMapper.writeValueAsString(error);
        writer.write(json);

        writer.flush();
        writer.close();
    }
}