package com.yc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 应用模块名称<p>
 * 代码描述<p>
 * Copyright: Copyright (C) 2019 XXX, Inc. All rights reserved. <p>
 *
 * @author yuche
 * @since 2019/12/29 19:43
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GateWayApplicationn {
    public static void main(String[] args) {
        SpringApplication.run(GateWayApplicationn.class, args);
    }
}
