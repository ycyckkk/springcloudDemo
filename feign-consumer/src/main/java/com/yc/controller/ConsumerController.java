package com.yc.controller;

import brave.Tracer;
import com.yc.service.Consumer;
import com.yc.service.HelloService;
import com.yc.service.OrginalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by yuche on 2019/10/20.
 */
@RestController
public class ConsumerController {

    private Logger logger = LoggerFactory.getLogger(ConsumerController.class);


    @Autowired
    HelloService helloService;

    @Autowired
    OrginalService orginalService;

    @Autowired
    Tracer tracer;

    @Autowired
    Consumer consumer;

    @RequestMapping(value = "/feign-consumer/getUserById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String helloConsumer(@PathVariable("id") String id) {
        logger.info("请求feign-consumer的getUserById方法");
        tracer.currentSpan().tag("用户", "张三");
        return helloService.hello(id);
    }


    @RequestMapping(value = "/feign-consumer1/getUserById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String helloConsumer1(@PathVariable("id") String id) {
        return orginalService.hello(id);
    }

    @RequestMapping(value = "/feign-consumer/getUserById2/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String helloConsumer2(@PathVariable("id") String id) {
        logger.info("请求feign-consumer的getUserById方法");
        tracer.currentSpan().tag("用户", "张三");
        return helloService.hello(id);
    }
}
