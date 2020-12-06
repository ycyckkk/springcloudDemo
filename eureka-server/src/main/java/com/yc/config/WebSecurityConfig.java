package com.yc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Author yucheng
 * @Date 2020/12/4 10:06
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        //对所有的请求通过httpBasic方式进行鉴权
        http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
    }
}
