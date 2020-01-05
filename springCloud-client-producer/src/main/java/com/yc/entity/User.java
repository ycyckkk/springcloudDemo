package com.yc.entity;

import lombok.Builder;
import lombok.Data;

/**
 * Created by yuche on 2019/9/7.
 */
@Builder
@Data
public class User{
    private String id;
    private String name;
    private String pwd;
    private String timeOut;
}
