package com.yc;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Hystrix作用：
 * 1）故障降级         使用场景：（防止故障蔓延）
 * 2）线程隔离
 *                     使用场景：（限流）
 *                     特性：（信号量隔离，线程池隔离）
 * 3) 请求合并和请求结果缓存
 */
/**
 * 常用来做：故障降级（），线程隔离（限流），请求合并和请求结果缓存（）
 * Created by yuche on 2019/9/8.
 */
@EnableCircuitBreaker
@SpringBootApplication
@EnableDiscoveryClient
public class ConsumerApplication {

    @Bean
    @LoadBalanced// 1、原理是什么
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /***
     * 客户端负载均衡
     * 常见策略：（轮询-默认，随机，权重-响应时长最短，最小并发请求，状态时可用的）
     * 自定义策略，实现IRule接口，重写choose方法
     * 常见配置
     * Eureka(ribbon.eureka.enabled,ribbon-config-demo.ribbon.listOfServers)
     * ConnectionTime，MaxTotalConnections，MaxConnectionsPerHost
     * 重试机制
     * @return
     */
    @Bean
    public IRule myRule() {
//        return new RandomRule();
        return new RoundRobinRule();//轮询

    }

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);

    }
}
