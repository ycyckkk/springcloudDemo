package com.yc.config;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 捕获全局的异常
 * @Author yucheng
 * @Date 2020/12/12 14:18
 */
@Slf4j
public class GlobalExceptionHandler implements HandlerExceptionResolver {

    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        warpperException(httpServletResponse, o, e);
        return new ModelAndView();
    }

    private void warpperException(HttpServletResponse httpServletResponse, Object o, Exception e) {
        //设置返回头
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", -1);
        jsonObject.put("message", "请求失败");

        PrintWriter printWriter = null;
        try {
            printWriter = httpServletResponse.getWriter();
            printWriter.print(jsonObject);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            printWriter.flush();
            printWriter.close();
        }
    }
}
