package com.yc.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.bouncycastle.asn1.ocsp.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by yuche on 2019/10/24.
 */
public class AccessFilter extends ZuulFilter {

    Logger logger = LoggerFactory.getLogger(AccessFilter.class);


    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        logger.info("send{} request to{}", request.getMethod(), request.getRequestURL().toString());
        Object accessToken = request.getParameter("accessToken");
        if (accessToken == null) {
            logger.warn("access token is empty");
            requestContext.setSendZuulResponse(false);  //拦截请求
            requestContext.setResponseStatusCode(401);  //返回编码
            requestContext.setResponseBody("Illegal Request");//返回描述
            requestContext.getResponse().setContentType("application/json; charset=utf-8");//返回体
            return null;
        }
        logger.info("access token is ok");
        return null;
    }
}
