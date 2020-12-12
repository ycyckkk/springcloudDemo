package com.yc.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author yucheng
 * @Date 2020/12/11 13:52
 */
@Data
public class Payment implements Serializable {

    private static final long serialVersionUID = -5826869635425416640L;
    private String paymentId;
    private Integer paymentStatus;
    private BigDecimal paymentAmount;
    private String paymentMember;

}
