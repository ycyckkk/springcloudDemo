package com.yc;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 定义Enable注解
 * @Author yucheng
 * @Date 2020/12/8 18:26
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({InterviewConfiguration.class})
public @interface EnableInterview {
}
