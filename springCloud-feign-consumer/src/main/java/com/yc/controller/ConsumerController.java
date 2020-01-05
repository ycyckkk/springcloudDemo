package com.yc.controller;

import com.yc.service.HelloService;
import com.yc.service.OrginalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by yuche on 2019/10/20.
 */
@RestController
public class ConsumerController {

    @Autowired
    HelloService helloService;

    @Autowired
    OrginalService orginalService;

    @RequestMapping(value = "/feign-consumer/getUserById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String helloConsumer(@PathVariable("id")String id)
    {
        return helloService.hello(id);
    }

    @RequestMapping(value = "/feign-consumer1/getUserById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String helloConsumer1(@PathVariable("id")String id)
    {
        return orginalService.hello(id);
    }
}
