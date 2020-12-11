package com.yc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * API网关=请求路由+断言工厂（匹配具体的请求路径）+过滤工厂（对请求进行额外的处理）
 * 进行：限流，重试，统一的异常处理
 * 底层是基于netty
 */
@SpringBootApplication
//@EnableDiscoveryClient
@EnableInterview
public class GateWayApplicationn {
    public static void main(String[] args) {
        SpringApplication.run(GateWayApplicationn.class, args);
    }
}
