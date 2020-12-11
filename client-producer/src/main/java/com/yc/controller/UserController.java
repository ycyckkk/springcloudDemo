package com.yc.controller;

import com.alibaba.fastjson.JSON;
import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.yc.entity.User;
import com.yc.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by yuche on 2019/9/7.
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private DiscoveryClient discoveryClient;
    @ApolloConfig
    private Config config;

    AtomicInteger ac = new AtomicInteger();
    private static Logger logger = LoggerFactory.getLogger(UserController.class);


    @RequestMapping(value = "/getUserById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getUserById(@PathVariable String id) {
        logger.info(String.valueOf(ac.addAndGet(1)));
//        tracer.currentSpan().tag("用户", "张三");
        /***
         * Hystrix默认超时时间2000毫秒
         */
//        int sleepTime = new Random().nextInt(3000);
//        try {
//            Thread.sleep(sleepTime);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        User user = userService.getUser(id);
//        User user1 = null;
//        user1.getId();
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        user.setTimeOut(String.valueOf(sleepTime));
        return JSON.toJSONString(user);
    }

    @RequestMapping(value = "/getWorkGroup", method = RequestMethod.GET)
    @ResponseBody
    public String getWorkGroup() {
        return config.getProperty("workGroupName", "defaultGroup");
    }
}
