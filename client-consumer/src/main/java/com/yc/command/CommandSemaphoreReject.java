package com.yc.command;


import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolProperties;

/**
 * 应用模块名称<p>
 * 代码描述<p>
 * Copyright: Copyright (C) 2019 XXX, Inc. All rights reserved. <p>
 *
 * @author yuche
 * @since 2019/12/24 22:03
 */
public class CommandSemaphoreReject extends HystrixCommand<String> {

    public String tag;

    /**
     * 线程池隔离
     * 适用于：
     * 优点：
     * 缺点：
     *
     * @param tag
     */
    public CommandSemaphoreReject(String tag) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("CommandReject")).
                andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionIsolationSemaphoreMaxConcurrentRequests(5).
                        withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)));
        this.tag = tag;
    }

    @Override
    public String run() throws Exception {
        Thread.sleep(1000);
        return tag;
    }

    @Override
    public String getFallback() {
        return "Semaphore降级处理";
    }
}
