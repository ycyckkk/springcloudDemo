//package com.yc.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.HandlerExceptionResolver;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import java.util.List;
//
///**
// * 配置拦截器
// * @Author yucheng
// * @Date 2020/12/12 14:43
// */
//@Configuration
//public class MyWebConfigurer implements WebMvcConfigurer {
//    @Override
//    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
//        resolvers.add(new GlobalExceptionHandler());
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        InterceptorRegistration registration = registry.addInterceptor(new Globalnterceptor());
//        registration.addPathPatterns("/**");
//        registration.excludePathPatterns("");
//    }
//}
