package com.example.ali.service.impl;

import com.alipay.api.AlipayApiException;
import com.example.ali.config.AlipayConfig;
import com.example.ali.entity.AlipayEntity;
import com.example.ali.service.IAlipayService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author iumyxF
 * @description:
 * @date 2023/6/26 9:30
 */
@Service
public class AlipayServiceImpl implements IAlipayService {

    @Resource
    private Alipay alipay;

    @Override
    public String aliPay(AlipayEntity alipayEntity) throws AlipayApiException {
        return alipay.payByPc(alipayEntity);
    }
}
