package com.yc.command;

import com.netflix.hystrix.*;
import org.springframework.web.client.RestTemplate;

/**
 * Created by yuche on 2019/9/13.
 */
public class UserCommand extends HystrixCommand<String> {

    private RestTemplate restTemplate;
    private Long id;

    public UserCommand(Setter setter, RestTemplate restTemplate, Long id) {
        super(setter);
        this.restTemplate = restTemplate;
        this.id = id;
    }

    @Override
    protected String run() throws Exception {
        return restTemplate.getForEntity("http://hello-service/getUserById/{1}", String.class, id).getBody();
    }

    @Override
    protected String getFallback() {
        return super.getFallback();
    }
}