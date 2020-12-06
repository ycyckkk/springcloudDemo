package com.yc.service;

import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by yuche on 2019/10/20.
 */
@FeignClient(value = "ribbon-consumer", fallback = UserRemoteClientFallback.class, configuration = FeignAutoConfiguration.class)
public interface Consumer {
    @RequestMapping(value = "/consumer", method = RequestMethod.GET)
    @ResponseBody
    String helloConsumer();

}
