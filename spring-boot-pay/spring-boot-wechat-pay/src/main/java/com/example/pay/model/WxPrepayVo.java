package com.example.pay.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author fzy
 * @description:
 * @date 2024/9/21 14:08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxPrepayVo {

    /**
     * 公众号ID
     */
    private String appId;

    /**
     * 时间戳，自1970年以来的秒数
     */
    private String timeStamp;

    /**
     * 随机串
     */
    private String nonceStr;

    /**
     * 预支付交易会话标识
     */
    private String packageVal;

    /**
     * 微信签名方式
     */
    private String signType;

    /**
     * 微信签名
     */
    private String paySign;
}
