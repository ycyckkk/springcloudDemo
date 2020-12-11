package com.yc.config;

import org.redisson.Redisson;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author yucheng
 * @Date 2020/12/10 18:07
 */
@Configuration
public class RedissionConfig {

//    @Value("${spring.redis.host}")
//    private String host;

    @Bean
    public RedissonClient createRedisson() {
        Config config = new Config();
//        config.useClusterServers().addNodeAddress("redis://8.129.220.152:6379");
        config.useSingleServer().setAddress("redis://8.129.220.152:6379");
        RedissonClient redisson = Redisson.create(config);
        return redisson;


    }

}
