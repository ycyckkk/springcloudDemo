package com.yc;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import com.yc.filter.AccessFilter;
import com.yc.filter.IpFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Created by yuche on 2019/9/8.
 */
@EnableZuulProxy
@SpringCloudApplication
/**
 * 作用：
 * 1）路由  集成eureka 使用场景：（路由到正确的服务，不需要手动去配置）
 * 2）认证  ZuulFilter
 *                     使用场景：（黑名单、token校验）
 *                     特性：（获取会话，拦截请求，设置返回code和返回信息，参数透传，拦截本地请求）
 * 3) 请求监控
 * 4）压力测试
 * 5）灰度发布
 */
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    @Bean
    public AccessFilter accessFilter() {
        return new AccessFilter();
    }
    @Bean
    public IpFilter ipFilter(){return  new IpFilter();}
}
