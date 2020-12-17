package com.yc;

import com.alibaba.fastjson.JSON;
import com.yc.entity.Payment;
import com.yc.service.PaymentService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Created by yuche on 2019/9/7.
 */
@SpringBootApplication
//@EnableFeignClients
@MapperScan("com.yc.mapper")
public class ProducerApplication {

    @Autowired
    private PaymentService paymentServiceImpl;


    public static void main(String[] args) {
//        System.setProperty("apollo.configService", "http://8.129.220.152:8080");
        SpringApplication.run(ProducerApplication.class, args);
    }

//    public void run(String... args) throws Exception {
//        //幂等
//        //高并发场景
//        //乐观锁：（版本号，key值控制，业务id）
//        //悲观锁：（分布式锁，数据库行锁）
//
//        Payment payment = new Payment();
//        payment.setPaymentStatus(2);
//        payment.setPaymentAmount(new BigDecimal(8));
//        payment.setPaymentId("6db9404e-3b8b-11eb-8a1b-0242ac110002");
//       paymentServiceImpl.updatePayment(payment);
//    }
}
