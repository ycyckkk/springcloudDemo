package com.yc.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author yucheng
 * @Date 2020/12/26 14:38
 */
@RestController
public class NacosSampleController {

    @NacosValue(value = "${userLocalCache:false}", autoRefreshed = true)
    private boolean userLocalCache;

    @GetMapping("/getCache")
    @ResponseBody
    public boolean getCache() {
        return userLocalCache;
    }
}
