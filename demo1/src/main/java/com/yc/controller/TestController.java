package com.yc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author yucheng
 * @Date 2021/3/7 15:11
 */
@RestController
public class TestController {

    @RequestMapping("/pay/wechat")
    public void wechatPay() {
        System.out.println("wechatPay");
    }

    @RequestMapping("/pay/miniapp")
    public void miniAppPay() {
        System.out.println("miniAppPay");
    }
}
