package com.example.ali.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author iumyxF
 * @description: 支付体
 * @date 2023/6/26 9:29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlipayEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商户订单号，必填
     */
    private String out_trade_no;

    /**
     * 订单名称，必填
     */
    private String subject;

    /**
     * 付款金额，必填
     * 根据支付宝接口协议，必须使用下划线
     */
    private String total_amount;

    /**
     * 商品描述，可空
     */
    private String body;

    /**
     * 超时时间参数
     */
    private String timeout_express = "10m";

    /**
     * 产品编号，如果是PC网页支付，这个是必传参数
     * 如果是扫码支付，这个是选传参数(而且值改为FACE_TO_FACE_PAYMENT)
     */
    private String product_code = "FAST_INSTANT_TRADE_PAY";
}
