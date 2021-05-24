package com.yc.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @Author yucheng
 * @Date 2021/3/7 19:44
 */
@Configuration
public class DataSourceConfig {

    @Primary
    @Bean(value = "firstDataSource")
    @ConfigurationProperties(value = "spring.datasource.druid.first")
    public DataSource firstDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(value = "secondDataSource")
    @ConfigurationProperties(value = "spring.datasource.druid.second")
    public DataSource secondDataSource() {
        return DataSourceBuilder.create().build();
    }
}
