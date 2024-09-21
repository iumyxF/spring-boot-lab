package com.example.pay.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author fzy
 * @description:
 * @date 2024/9/21 14:06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentInfoForm {

    /**
     * 乘客微信openid
     */
    private String customerOpenId;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 付款方式：1-微信
     */
    private Integer payWay;

    /**
     * 支付金额
     */
    private BigDecimal amount;

    /**
     * 交易内容
     */
    private String content;
}
