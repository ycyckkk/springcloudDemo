package com.yc;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author yucheng
 * @Date 2020/12/26 14:35
 */
@SpringBootApplication
@NacosPropertySource(dataId = "example", autoRefreshed = true)
public class NacosServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(NacosServerApplication.class, args);
    }
}
