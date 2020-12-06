package com.yc;

import com.yc.filter.LimitFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * @Author yucheng
 * @Date 2020/12/4 15:39
 */
@SpringBootApplication
@EnableZuulProxy
public class ZuulDemoApplication {
    public static void main(String[] args) {
        System.setProperty("apollo.configService", "http://8.129.220.152:8080");
        SpringApplication.run(ZuulDemoApplication.class, args);
    }

    @Bean
    public LimitFilter limitFilter() {
        return new LimitFilter();
    }
}
