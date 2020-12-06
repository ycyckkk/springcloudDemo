package com.yc.command;

import com.netflix.hystrix.*;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 应用模块名称<p>
 * 代码描述<p>
 * Copyright: Copyright (C) 2019 XXX, Inc. All rights reserved. <p>
 *
 * @author yuche
 * @since 2019/12/29 17:12
 */
public class CacheCommand extends HystrixCommand<String> {

    Logger logger = LoggerFactory.getLogger(CacheCommand.class);
    private static final HystrixCommandKey GETTER_KEY = HystrixCommandKey.Factory.asKey("MyCacheKey");
    private static final HystrixCommandGroupKey GROUP_KEY = HystrixCommandGroupKey.Factory.asKey("MyCacheGroup");
    private final String name;

    public static void flushCache(String key) {
        HystrixRequestCache.getInstance(GETTER_KEY, HystrixConcurrencyStrategyDefault.getInstance()).clear(key);
    }

    public CacheCommand(String name) {
        super((Setter.withGroupKey(GROUP_KEY)).andCommandKey(GETTER_KEY));
        this.name = name;
    }

    @Override
    public String run() throws Exception {
        String msg = String.format("threadId={%s},threadName={%s},name={%s}",
                Thread.currentThread().getId(), Thread.currentThread().getName(), this.name);
        return msg;
    }

    @Override
    public String getCacheKey() {
        return String.valueOf(name);
    }
}
