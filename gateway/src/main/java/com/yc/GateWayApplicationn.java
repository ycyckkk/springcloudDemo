package com.yc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Mono;

/**
 * API网关=请求路由+断言工厂（匹配具体的请求路径）+过滤工厂（对请求进行额外的处理）
 * 进行：限流，重试，统一的异常处理
 * 底层是基于netty
 */
@SpringBootApplication
//@EnableDiscoveryClient
//@EnableInterview
public class GateWayApplicationn {
    public static void main(String[] args) {
        SpringApplication.run(GateWayApplicationn.class, args);
    }

    //    @Bean
//    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
//        return builder.routes().build();
        //先进行路由，然后进行请求过滤
//        return builder.routes().route(p -> p.path("/get").filters(f -> f.addRequestHeader("Hello", "World")).uri("http://httpbin.org:80/"))
//                .route(p -> p.host("/*.hystrix.com").filters(f -> f.hystrix(config -> config.setName("mycmd"))).uri("http://httpbin.org:80/"))
//                .build();
//    }

        @Bean
        public KeyResolver ipKeyResolver () {
            return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
        }

    }
