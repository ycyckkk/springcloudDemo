package com.yc.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author yucheng
 * @Date 2020/12/11 9:06
 */
// fallback = UserRemoteClientFallback.class, configuration = FeignAutoConfiguration.class
@FeignClient(value = "hello-service")
@Service
public interface HelloService {
    @RequestMapping(value = "/getUserById/{id}", method = RequestMethod.GET)
    @ResponseBody
    String hello(@PathVariable("id") String id);
}
