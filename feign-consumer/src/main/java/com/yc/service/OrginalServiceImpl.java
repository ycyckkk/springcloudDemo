package com.yc.service;

import com.alibaba.fastjson.JSON;
import com.sun.xml.internal.messaging.saaj.packaging.mime.util.BASE64EncoderStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;


/**
 * 想知道某个功能的好处，先踩坑，写原生的代码
 * 应用模块名称<p>
 * 代码描述<p>
 * Copyright: Copyright (C) 2020 XXX, Inc. All rights reserved. <p>
 *
 * @author yuche
 * @since 2020/1/5 13:41
 */
@Service("orginalService")
public class OrginalServiceImpl implements OrginalService {

    @Autowired
    private RestTemplate restTemplate;

    public String hello(String id) {
        String url = "http://hello-service/getUserById/{id}";

        //设置参数转换器
        List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
        messageConverters.remove(6);
        messageConverters.add(6, new MappingJackson2HttpMessageConverter());

        //设置请求头
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
//        String loginInfo = null;
//        try {
//            loginInfo = (new BASE64Encoder()).encodeBuffer(("yucheng" + ":" + "721133").getBytes("UTF-8"));
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        headers.set("Authorization", "Basic" + loginInfo);

        //uriVariables 对应 pathVariable
        HttpEntity<String> requestEntity = new HttpEntity<String>(null, headers);
        String results = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class, id).getBody();
//        String result1 = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class, new Object[]{}).getBody();

        return results;
    }
}
