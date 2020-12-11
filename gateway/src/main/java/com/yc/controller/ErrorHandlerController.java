package com.yc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RestController;

/**
 * 实现该接口，返回自定义的异常信息
 * @Author yucheng
 * @Date 2020/12/6 17:02
 */
@RestController
public class ErrorHandlerController implements ErrorController {
    @Autowired
    ErrorAttributes errorAttributes;

    public String getErrorPath() {
        return null;
    }
}
