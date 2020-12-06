package com.yc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Author yucheng
 * @Date 2020/12/5 20:40
 */
@Configuration
public class BasicConf {
    @Value("${clusterLimitRate:10}")
    private Integer clusterLimitRate;

    public Integer getClusterLimitRate() {
        return clusterLimitRate;
    }

    public void setClusterLimitRate(Integer clusterLimitRate) {
        this.clusterLimitRate = clusterLimitRate;
    }
}
