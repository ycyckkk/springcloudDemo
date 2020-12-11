package com.yc;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author yucheng
 * @Date 2020/12/8 17:43
 */
@ConfigurationProperties(prefix = "interview")
@Data
public class InterviewProperties {

     //公司名
    private String company;

    //金额
    private Integer salary;

    //位置
    private String address;


    @Override
    public String toString() {
        return "InterviewProperties{" +
                "company='" + company + '\'' +
                ", salary=" + salary +
                ", address='" + address + '\'' +
                '}';
    }
}
