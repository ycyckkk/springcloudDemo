package com.yc.service.impl;

import com.google.common.collect.Maps;
import com.yc.service.TestService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Author yucheng
 * @Date 2020/12/16 16:43
 */
@Service
public class TestServiceImpl implements TestService {

    @Override
    public String testOOM(int number) {
        Map<String, String> map = Maps.newHashMap();
        for (int i = 0; i < number; i++) {
            map.put(i + "", "1");
            if (i % 5000 == 0) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return String.valueOf(map.size());
    }

    @Override
    public void testDeadLock() {
    }

    @Override
    public void testBlock() {
        System.out.println("123");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("234");
    }
}
