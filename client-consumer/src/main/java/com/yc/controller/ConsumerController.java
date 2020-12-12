package com.yc.controller;

import brave.Tracer;
import com.alibaba.fastjson.JSON;
import com.netflix.hystrix.*;
import com.netflix.hystrix.HystrixCommand.Setter;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import com.yc.command.*;
import com.yc.service.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by yuche on 2019/9/8.
 */
@RestController
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private HelloService helloService;

    @Autowired
    Tracer tracer;

    private static Logger logger = LoggerFactory.getLogger(ConsumerController.class);


    /***
     * 实现1：直接通过HystrixCommand直接，并指定fallbackMethod降级方法，同步
     * @return
     */

    @HystrixCommand(fallbackMethod = "fallbackMethod1")
    @RequestMapping(value = "/consumer", method = RequestMethod.GET)
    public String helloConsumer() {
        logger.info("我是consumer");
        return restTemplate.getForEntity("http://hello-service/getUserById/{1}", String.class, "1").getBody();
    }

    /**
     * 异步
     *
     * @return
     * @throws Exception
     */
    @HystrixCommand
    @RequestMapping(value = "/consumer2", method = RequestMethod.GET)
    public String helloConsumerAsync() throws Exception {
        return new Callable<String>() {
            public String call() throws Exception {
                return restTemplate.getForEntity("http://hello-service/getUserById/{1}", String.class, "1").getBody();
            }
        }.call();
    }

    @RequestMapping(value = "/getServiceInfo", method = RequestMethod.GET)
    public String getServiceInfo() {
        ServiceInstance serviceInstance = loadBalancerClient.choose("hello-service");
        URI uri = URI.create(String.format("http://%s:%s", serviceInstance.getHost(), serviceInstance.getPort()));
        return JSON.toJSONString(uri);
    }

    @RequestMapping(value = "/getCache", method = RequestMethod.GET)
    public String getCache() throws Exception {

        // TODO 先写这里吧
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("第一次Value=");
        String name = "getCache";

        String result = new CacheCommand(name).execute();
        stringBuilder.append(result);
        result = new CacheCommand(name).queue().get();
        stringBuilder.append(";第二次Value=");
        stringBuilder.append(result);
        return JSON.toJSONString(stringBuilder.toString());
    }

    @RequestMapping(value = "/getCache1", method = RequestMethod.GET)
    public String getCache1() throws Exception {

        // TODO 先写这里吧
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        StringBuilder stringBuilder = new StringBuilder();
        String name = "getCache1";
        stringBuilder.append("第一次Value=");
        String result = new CacheCommand(name).execute();
        stringBuilder.append(result);
        CacheCommand.flushCache(name);
        result = new CacheCommand(name).queue().get();
        stringBuilder.append(";第二次Value=");
        stringBuilder.append(result);
        return JSON.toJSONString(stringBuilder.toString());
    }

    @RequestMapping(value = "/getCollapser", method = RequestMethod.GET)
    public String getCollapser() throws Exception {

        // TODO 先写这里吧
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        Future<String> f1 = new UserCollapser("zhangsan").queue();
        Future<String> f2 = new UserCollapser("zhangsan333").queue();
        return JSON.toJSONString(f1.get() + "," + f2.get());

    }

    /***
     * 实现2：通过继承HystrixCommand，并且重写run方法实现
     * @retur
     */
    @RequestMapping(value = "/consumer1", method = RequestMethod.GET)
    public String helloConsumer1() {
        String THREAD_POOL_PREFIX = "thread-";
        String groupKey = "groud-100";
        int timeoutMilliseconds = 2000;

        Setter setter = Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(groupKey))
                .andCommandKey(HystrixCommandKey.Factory.asKey(groupKey))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey(THREAD_POOL_PREFIX + groupKey))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(timeoutMilliseconds))
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter().withCoreSize(10)./*withMaxQueueSize(90).*/withQueueSizeRejectionThreshold(1000));

        // 每次调用execute方法都需要创建一个新的HelloCommand实例，因为每个实例都有自己的状态
        /*if (!commandState.compareAndSet(CommandState.NOT_STARTED, CommandState.OBSERVABLE_CHAIN_CREATED)) {
            IllegalStateException ex = new IllegalStateException("This instance can only be executed once. Please instantiate a new instance.");
            //TODO make a new error type for this
            throw new HystrixRuntimeException(FailureType.BAD_REQUEST_EXCEPTION, _cmd.getClass(), getLogMessagePrefix() + " command executed multiple times - this is not permitted.", ex, null);
        }*/
        //同步执行
        UserCommand userCommand = new UserCommand(setter, restTemplate, (long) 1);
        String result = userCommand.execute();

        //异步执行
        Future<String> stringFuture = new UserCommand(setter, restTemplate, (long) 1).queue();
        try {
            String result1 = stringFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }


    @RequestMapping(value = "/testHystrixCommand", method = RequestMethod.GET)
    public void testHystrixCommand() {
        for (int i = 0; i < 10; i++) {
            new TestAsyncThread(i).start();
        }
    }

    @RequestMapping(value = "/testSemaphoreHystrixCommand", method = RequestMethod.GET)
    public void testSemaphoreHystrixCommand() {
        for (int i = 0; i < 10; i++) {
            new TestAsyncSemaphore(i).start();
        }
    }

    @RequestMapping(value = "/helloService12", method = RequestMethod.GET)
    public String helloService12() {
        return helloService.hello("11");
    }

    private class TestThread extends Thread {
        private int index;

        public TestThread(int index) {
            this.index = index;
        }

        @Override
        public void run() {

            //Thread 测试
//            CommandThreadReject breaker = new CommandThreadReject("success");
//            System.out.println("第" + (index + 1) + "次请求，结果为：" + breaker.execute());

            //semaphore测试
            CommandSemaphoreReject semaphore = new CommandSemaphoreReject("success");
            System.out.println("第" + (index + 1) + "次请求，结果为：" + semaphore.execute());

        }
    }

    private class TestAsyncThread extends Thread {
        private int index;

        public TestAsyncThread(int index) {
            this.index = index;
        }

        @Override
        public void run() {
            CommandThreadReject breaker = new CommandThreadReject("success");
            Future<String> future = breaker.queue();
            try {
                System.out.println("第" + (index + 1) + "次请求，结果为：" + future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    private class TestAsyncSemaphore extends Thread {
        private int index;

        public TestAsyncSemaphore(int index) {
            this.index = index;
        }

        @Override
        public void run() {
            CommandSemaphoreReject semaphore = new CommandSemaphoreReject("success");
            String resut = semaphore.execute();
            System.out.println("第" + (index + 1) + "次请求，结果为：" + resut);

//            semaphore.queue();
//            Future<String> future = breaker.queue();
//            try {
//                System.out.println("第" + (index + 1) + "次请求，结果为：" + future.get());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
        }
    }

    public String fallbackMethod() {
        return "error";
    }

    public String fallbackMethod1() {
        return "网络异常";
    }
}
