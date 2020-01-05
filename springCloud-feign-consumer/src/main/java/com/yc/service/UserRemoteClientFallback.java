package com.yc.service;

import org.springframework.stereotype.Component;

/**
 * 应用模块名称<p>
 * 代码描述<p>
 * Copyright: Copyright (C) 2019 XXX, Inc. All rights reserved. <p>
 *
 * @author yuche
 * @since 2019/12/29 19:11
 */
@Component
public class UserRemoteClientFallback implements HelloService {
    public String hello(String id) {
        return "failed";
    }
}
