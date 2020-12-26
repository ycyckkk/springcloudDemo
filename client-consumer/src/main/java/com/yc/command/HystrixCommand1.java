package com.yc.command;


import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

/**
 * 默认使用线程池方式，进行调用
 * @Author yucheng
 * @Date 2020/12/22 21:21
 */
public class HystrixCommand1 extends HystrixCommand<String> {

    private String paymentId;

    @Autowired
    private RestTemplate restTemplate;

    public HystrixCommand1(String paymentId) {
        super(HystrixCommandGroupKey.Factory.asKey("GetPayment"));
        this.paymentId = paymentId;
    }

    public String run() throws Exception {
        return restTemplate.getForEntity("http://hello-service/getUserById/{1}", String.class, "1").getBody();
    }
}
