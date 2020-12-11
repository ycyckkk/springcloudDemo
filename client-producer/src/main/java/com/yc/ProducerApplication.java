package com.yc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Created by yuche on 2019/9/7.
 */
@SpringBootApplication
@EnableFeignClients
public class ProducerApplication {

    public static void main(String[] args) {
        System.setProperty("apollo.configService","http://8.129.220.152:8080");
        SpringApplication.run(ProducerApplication.class, args);
    }
}
