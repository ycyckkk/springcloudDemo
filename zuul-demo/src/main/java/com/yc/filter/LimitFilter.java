package com.yc.filter;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.yc.config.BasicConf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Author yucheng
 * @Date 2020/12/5 11:50
 */
//@Configuration
public class LimitFilter extends ZuulFilter {

    Logger logger = LoggerFactory.getLogger(LimitFilter.class);

    @Autowired
    @Qualifier("longRedisTemplate")
    private RedisTemplate<String, Long> redisTemplate;

//    private final RedisTemplate<String, Serializable> limitRedisTemplate;
//
//    @Autowired
//    public LimitFilter(RedisTemplate<String, Serializable> limitRedisTemplate) {
//        this.limitRedisTemplate = limitRedisTemplate;
//    }

    @Autowired
    private BasicConf basicConf;

    public static volatile RateLimiter rateLimiter = RateLimiter.create(100.0);

    public String filterType() {
        return "pre";
    }

    public int filterOrder() {
        return 0;
    }

    public boolean shouldFilter() {
        return true;
    }

    //increaseBy
    public Object run() {
        try {
            Date date = DateUtil.date();
            String dateStr = DateUtil.format(date, "yyyy-MM-dd HH:mm");
            RequestContext requestContext = RequestContext.getCurrentContext();
            Long second = System.currentTimeMillis() / 1000;
            String key = "fsh-api-rate-limit-" + dateStr;
            if (!redisTemplate.hasKey(key)) {
                redisTemplate.opsForValue().set(key, 0L, 100, TimeUnit.SECONDS);
            }
            if (redisTemplate.opsForValue().increment(key, 1) > basicConf.getClusterLimitRate()) {
                requestContext.setSendZuulResponse(false);
                requestContext.set("isSuccess", false);
                requestContext.setResponseBody(JSON.toJSONString("当前负载太高，请稍后重试"));
                requestContext.getResponse().setContentType("application/json;charset=utf-8");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            rateLimiter.acquire();
        }
        return null;
    }


}
