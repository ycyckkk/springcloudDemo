package com.yc.controller;

import com.alibaba.fastjson.JSON;
import com.yc.entity.Payment;
import com.yc.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author yucheng
 * @Date 2020/12/12 11:44
 */
@RestController
public class PaymentController {
    @Autowired
    private HelloService helloService;

    //controller层代码太多
    @PostMapping(value = "/updatePayment")
    public Integer updatePayment(@RequestBody Payment payment) {
        helloService.updatePayment(payment);
        return 1;
    }

    @GetMapping(value = "/queryPayment/{id}")
    @ResponseBody
    public String getPayment(@PathVariable(value = "id") String id) {
        return JSON.toJSONString(helloService.getPayment(id));
    }
}
