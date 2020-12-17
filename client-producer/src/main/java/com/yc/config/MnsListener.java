package com.yc.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.*;

/**
 * @Author yucheng
 * @Date 2020/12/16 21:40
 */
public class MnsListener implements ApplicationListener<ContextRefreshedEvent> {

    private ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("listener-%d").build();

    private ExecutorService listenerExe = new ThreadPoolExecutor(4, 20, 0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(1024), threadFactory, new ThreadPoolExecutor.AbortPolicy());


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "115.159.100.145:9092");
        props.setProperty("group.id", "test");
        props.setProperty("enable.auto.commit", "true");
        props.setProperty("auto.commit.interval.ms", "1000");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("foo", "bar","my-topic"));

        listenerExe.execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("12345");
                    ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                    for (ConsumerRecord<String, String> record : records)
                        System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
                }
            }
        });
    }
}
