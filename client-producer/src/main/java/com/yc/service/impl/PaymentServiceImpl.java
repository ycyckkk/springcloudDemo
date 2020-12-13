package com.yc.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.yc.entity.Payment;
import com.yc.mapper.PaymentMapper;
import com.yc.service.PaymentService;
import org.redisson.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created by yuche on 2019/9/7.
 */
@Service("paymentService")
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentMapper paymentMapper;

    @Autowired
    private RedissonClient redissonClient;

//    public void addPayment() {
//    }

    /**
     * 修改支付信息，通过redis保证幂等性，防止高并发场景下重复请求和消息重复消费的情况
     * 非高并发场景可以使用分布式锁或者数据库乐观锁or死锁进行控制
     *
     * @param payment
     */
    public void updatePayment(Payment payment) {
        long time = System.currentTimeMillis();
        String key = payment.getPaymentId() + ":" + time;
        RBuckets buckets = redissonClient.getBuckets();
        Map<String, String> map = buckets.get(key);
        System.out.println("map1=" + JSON.toJSONString(map));
        redissonClient.getBucket(key).set(1, 60, TimeUnit.SECONDS);
        Map<String, String> map1 = buckets.get(key);
        System.out.println("map2=" + JSON.toJSONString(map1));
        paymentMapper.update(payment, new UpdateWrapper<Payment>().eq("payment_id", payment.getPaymentId()));
    }

    //思路：先查缓存，在查布隆过滤器，在查本地缓存，在查数据库
    public String getPayment(String id) {
        //先查缓存
//        RBucket<String> bucket = redissonClient.getBucket("payment");
        RMap map = redissonClient.getMap("payment");
        if (map != null) {
            String str = (String) map.get(id);
            if (null!= str && !str.isEmpty()) {
                return str;
            }
        }

        //为空就查布隆过滤器，不存在就直接返回
        RBloomFilter<String> bloomFilter = redissonClient.getBloomFilter("sample");
        if (!bloomFilter.contains(id)) {
            return "";
        }

        //存在则查询数据库
        Wrapper<Payment> wrapper = new QueryWrapper<Payment>().eq("payment_id", id);
        String result = JSON.toJSONString(paymentMapper.selectOne(wrapper));
        //放入缓存中
        map.putAsync(id, result);
        return result;
    }

    @Override
    public void initPayment() {
        List<Payment> list = paymentMapper.selectList(null);
        if (!list.isEmpty()) {
//            List<String> paymentIds = list.stream().map(payment -> payment.getPaymentId()).collect(Collectors.toList());
            RBloomFilter<String> bloomFilter = redissonClient.getBloomFilter("sample");
            bloomFilter.tryInit(1000000L, 0.02);
            list.forEach(payment -> bloomFilter.add(payment.getPaymentId()));
        }
    }


}
