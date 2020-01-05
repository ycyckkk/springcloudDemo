package com.yc.command;

import com.google.common.collect.Lists;
import com.netflix.hystrix.HystrixCollapser;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;

/**
 * 应用模块名称<p>
 * 代码描述<p>
 * Copyright: Copyright (C) 2019 XXX, Inc. All rights reserved. <p>
 *
 * @author yuche
 * @since 2019/12/29 17:48
 */
public class UserCollapser extends HystrixCollapser<List<String>, String, String> {

    Logger logger = LoggerFactory.getLogger(UserCollapser.class);
    private static final HystrixCommandKey COLLAPSER_KEY = HystrixCommandKey.Factory.asKey("CollapserKey");
    private static final HystrixCommandGroupKey COLLAPSER_GROUP_KEY = HystrixCommandGroupKey.Factory.asKey("CollapserGroupKey");

    private final String name;

    public UserCollapser(String name) {
        this.name = name;
    }

    @Override
    public String getRequestArgument() {
        return name;
    }

    @Override
    protected HystrixCommand<List<String>> createCommand(Collection<CollapsedRequest<String, String>> collection) {
        return new BatchCommand(collection);
    }

    @Override
    protected void mapResponseToRequests(List<String> strings, Collection<CollapsedRequest<String, String>> collection) {
        int count = 0;
        for (CollapsedRequest<String, String> request : collection) {
            request.setResponse(strings.get(count++));
        }
    }

    //请求内部类
    private static final class BatchCommand extends HystrixCommand<List<String>> {
        private final Collection<CollapsedRequest<String, String>> requests;

        private BatchCommand(Collection<CollapsedRequest<String, String>> requests) {
            super(Setter.withGroupKey(COLLAPSER_GROUP_KEY).andCommandKey(COLLAPSER_KEY));
            this.requests = requests;
        }

        @Override
        protected List<String> run() throws Exception {
            System.out.println("请求开始");
            List<String> responseList = Lists.newArrayList();
            for (CollapsedRequest<String, String> request : requests) {
                responseList.add("返回结果:" + request.getArgument());
            }
            return responseList;
        }
    }
}
