package com.example.ali.service;

import com.alipay.api.AlipayApiException;
import com.example.ali.entity.AlipayEntity;

/**
 * @author fzy
 * @description:
 * @date 2023/6/26 9:29
 */
public interface IAlipayService {

    /**
     * 支付宝支付接口
     *
     * @param alipayEntity
     * @return 结果
     * @throws AlipayApiException
     */
    String aliPay(AlipayEntity alipayEntity) throws AlipayApiException;

}
