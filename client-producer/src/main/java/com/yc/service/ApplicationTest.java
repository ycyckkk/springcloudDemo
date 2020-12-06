package com.yc.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 应用模块名称<p>
 * 代码描述<p>
 * Copyright: Copyright (C) 2019 XXX, Inc. All rights reserved. <p>
 *
 * @author yuche
 * @since 2019/12/26 1:29
 */
public class ApplicationTest implements ApplicationContextAware {

    @Autowired
    private UserService userService;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("bababala");
    }
}
