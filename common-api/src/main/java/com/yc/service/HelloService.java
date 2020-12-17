package com.yc.service;

import com.yc.entity.Payment;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

/**
 * @Author yucheng
 * @Date 2020/12/11 9:06
 */
// fallback = UserRemoteClientFallback.class, configuration = FeignAutoConfiguration.class
//@FeignClient(value = "hello-service")
@Service
public interface HelloService {
    @RequestMapping(value = "/getUserById/{id}", method = RequestMethod.GET)
    @ResponseBody
    String hello(@PathVariable("id") String id);

    //    public void addPayment();
    @PostMapping(value = "/updatePayment")
    public void updatePayment(@RequestBody Payment payment);

    @GetMapping(value = "/queryPayment/{id}")
    @ResponseBody
    public Payment getPayment(@PathVariable(value = "id") String id);
}
