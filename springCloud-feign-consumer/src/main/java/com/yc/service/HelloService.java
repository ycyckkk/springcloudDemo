package com.yc.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by yuche on 2019/10/20.
 */
@FeignClient(value = "hello-service", fallback = UserRemoteClientFallback.class)
public interface HelloService {
    @RequestMapping(value = "/getUserById/{id}", method = RequestMethod.GET)
    @ResponseBody
    String hello(@PathVariable("id") String id);

}
