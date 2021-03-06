package com.yc.service;

import com.yc.entity.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

/**
 * @Author yucheng
 * @Date 2020/12/11 13:52
 */
@FeignClient(value = "hello-service")
@Service
public interface PaymentService{

    //    public void addPayment();
    @PostMapping(value = "/updatePayment")
    public void updatePayment(@RequestBody Payment payment);

    @GetMapping(value = "/queryPayment/{id}")
    // TODO
//    @ResponseBody
    public String getPayment(@PathVariable(value = "id") String id);


    @PostMapping(value = "/initPayment")
    public void initPayment();

}
