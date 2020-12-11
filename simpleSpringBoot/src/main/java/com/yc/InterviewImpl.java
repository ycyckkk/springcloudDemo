package com.yc;

import com.alibaba.fastjson.JSON;

/**
 * @Author yucheng
 * @Date 2020/12/8 17:53
 */
public class InterviewImpl implements Interview {

    private InterviewProperties properties;

    public InterviewImpl(InterviewProperties properties) {
        this.properties = properties;
    }

    public boolean joinInterview(InterviewProperties properties) {
        System.out.println("去面试，面试内容" + JSON.toJSONString(properties));
        return true;
    }
}
