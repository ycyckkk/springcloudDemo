package com.yc.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * @Author yucheng
 * @Date 2021/3/6 19:25
 */
@Component
@Slf4j
public class KafkaProducer {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void sendMessage(String topic, Object object) {
        ListenableFuture<SendResult<String, Object>> listenableFuture = kafkaTemplate.send(topic, object);
        listenableFuture.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            public void onFailure(Throwable ex) {
                log.info("消息发送失败", ex.getMessage());
            }

            public void onSuccess(SendResult<String, Object> result) {
                log.info("消息发送成功", result.toString());
            }
        });
    }

}
