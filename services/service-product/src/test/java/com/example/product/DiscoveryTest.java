package com.example.product;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import com.alibaba.cloud.nacos.discovery.NacosServiceDiscovery;
import com.alibaba.nacos.api.exception.NacosException;

@SpringBootTest
public class DiscoveryTest {
    @Autowired
    private DiscoveryClient discoveryClient;

    @Test
    void discoveryClientTest() {
        for (var s : discoveryClient.getServices()) {
            System.out.println(s);
            List<ServiceInstance> instances = discoveryClient.getInstances(s);
            for (var i : instances) {
                System.out.println(i.getHost() + ", " + i.getPort());
            }
        }
    }

    @Autowired
    NacosServiceDiscovery nacosServiceDiscovery;

    @Test
    void nacosServiceDiscoveryTest() throws NacosException {
        for (String service : nacosServiceDiscovery.getServices()) {
            System.out.println("service = " + service);
            List<ServiceInstance> instances = nacosServiceDiscovery.getInstances(service);
            for (ServiceInstance instance : instances) {
                System.out.println("ip：" + instance.getHost() + "；" + "port = " + instance.getPort());
            }
        }
    }
}
