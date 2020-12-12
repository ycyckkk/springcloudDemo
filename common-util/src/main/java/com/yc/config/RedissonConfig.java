package com.yc.config;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @Author yucheng
 * @Date 2020/12/11 11:52
 */
@Configuration
public class RedissonConfig {
    @Bean
    public RedissonClient createRedisson() {
//        Resource resource = new ClassPathResource("classpath:config.yaml");
        Config config = new Config();
        config.useSingleServer().setAddress("redis://8.129.220.152:6379");
        RedissonClient redissonClient = Redisson.create(config);
//        //正常加锁
//        RLock normalLock = redissonClient.getLock("normalLock");
//        normalLock.lock(10, TimeUnit.SECONDS);
//
//        //尝试进行加锁（等待100秒，持有10秒后自动释放）
//        RLock tryLock = redissonClient.getLock("tryLock");
//        try {
//            boolean result = tryLock.tryLock(100, 10, TimeUnit.SECONDS);
//            if (result) {
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } finally {
//            tryLock.unlock();
//        }
        return redissonClient;
    }
}
