package com.yc;

import com.yc.config.RedissionConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author yucheng
 * @Date 2020/12/10 18:18
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedissionConfig.class)
public class RedissionTest {

    @Autowired
    private RedissonClient redissonClient;

    @Test
    public void test1() {
//        RAtomicLong rAtomicLong = redissonClient.getAtomicLong("myLong");
//        rAtomicLong.getAndAdd(1);

        RMap<String, String> map = redissonClient.getMap("myMap");
//        map.put("test1","test2");
//        System.out.println(map.get("test1"));
    }
}
