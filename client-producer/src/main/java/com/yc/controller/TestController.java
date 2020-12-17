package com.yc.controller;

import com.yc.entity.Payment;
import com.yc.service.MqService;
import com.yc.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author yucheng
 * @Date 2020/12/16 16:49
 */
@RestController
public class TestController {
    @Autowired
    private TestService testServiceImpl;

    @Autowired
    private MqService mqServiceImpl;

    @GetMapping(value = "/testOOM/{num}")
    public String testOOM(@PathVariable String num) {
        int number = Integer.valueOf(num);
        return testServiceImpl.testOOM(number);
    }

    @GetMapping(value = "/testBlock")
    public String testBlock() {
        testServiceImpl.testBlock();
        return "1";
    }

    @GetMapping(value = "/sendMq")
    public String sendMq() {
        mqServiceImpl.sendMq();
        return "1";
    }

    @GetMapping(value = "/acceptMq")
    public String acceptMq() {
        mqServiceImpl.acceptMq();
        return "1";
    }
}
