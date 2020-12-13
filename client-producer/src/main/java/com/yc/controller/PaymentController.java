package com.yc.controller;

import com.alibaba.fastjson.JSON;
import com.yc.entity.Payment;
import com.yc.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author yucheng
 * @Date 2020/12/11 16:41
 */
@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentServiceImpl;

    @GetMapping(value = "/queryPayment/{id}")
    public String queryPayment(@PathVariable(value = "id") String id) {
        return paymentServiceImpl.getPayment(id);
    }

    @PostMapping(value = "/updatePayment")
    public String updatePayment(@RequestBody Payment payment) {
        paymentServiceImpl.updatePayment(payment);
        return "1";
    }

    @GetMapping(value = "/initPayment/{id}")
    public String initPayment(@PathVariable(value = "id") String id) {
        paymentServiceImpl.initPayment();
        return "1";
    }
}
