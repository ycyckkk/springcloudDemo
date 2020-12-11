package com.yc;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author yucheng
 * @Date 2020/12/8 17:51
 */
@Configuration
@EnableConfigurationProperties(value = InterviewProperties.class)
public class InterviewConfiguration {

    @Bean
    public InterviewImpl interview(InterviewProperties properties) {
        return new InterviewImpl(properties);
    }
}
