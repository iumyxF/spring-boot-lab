package com.example.pay.service;

import com.example.pay.model.PaymentInfoForm;
import com.example.pay.model.WxPrepayVo;

/**
 * @author fzy
 * @description:
 * @date 2024/8/15 14:04
 */
public interface WeChatPayService {

    /**
     * 创建微信支付订单
     *
     * @param paymentInfoForm 支付信息
     * @param spbillCreateIp  终端IP
     * @return 预支付订单信息
     */
    WxPrepayVo createWxPayment(PaymentInfoForm paymentInfoForm, String spbillCreateIp);

    /**
     *
     * @param xmlData
     */
    void parseOrderNotifyResult(String xmlData);
}
