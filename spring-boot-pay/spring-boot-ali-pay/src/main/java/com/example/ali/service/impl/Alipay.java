package com.example.ali.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.example.ali.config.AlipayConfig;
import com.example.ali.entity.AlipayEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author fzy
 * @description: 支付宝支付接口
 * @date 2023/6/26 9:37
 */
@Slf4j
@Service
public class Alipay {

    @Resource
    private AlipayConfig alipayConfig;

    /**
     * 根据支付类型不同，调用的支付接口也会不同，而且必填参数也会不同
     */
    public String payByPc(AlipayEntity alipayEntity) throws AlipayApiException {
        AlipayClient alipayClient = getAlipayClient();
        // 2、设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        // 页面跳转同步通知页面路径
        alipayRequest.setReturnUrl(alipayConfig.getReturnUrl());
        // 服务器异步通知页面路径
        alipayRequest.setNotifyUrl(alipayConfig.getNotifyUrl());
        // 封装参数
        alipayRequest.setBizContent(JSON.toJSONString(alipayEntity));
        log.info("封装请求支付宝付款参数为:{}", JSON.toJSONString(alipayRequest));
        // 3、请求支付宝进行付款，并获取支付结果
        String result = alipayClient.pageExecute(alipayRequest).getBody();
        // 返回付款信息
        return result;
    }

    /**
     * 二维码支付
     */
    public AlipayTradePrecreateResponse payByMobile(AlipayEntity alipayEntity) throws AlipayApiException {
        AlipayClient alipayClient = getAlipayClient();
        //扫码支付使用AlipayTradePrecreateRequest传参，下面调用的是execute方法
        AlipayTradePrecreateRequest precreateRequest = new AlipayTradePrecreateRequest();
        precreateRequest.setReturnUrl(alipayConfig.getReturnUrl());
        precreateRequest.setNotifyUrl(alipayConfig.getNotifyUrl());
        precreateRequest.setBizContent(JSON.toJSONString(alipayEntity));
        log.info("封装请求支付宝付款参数为:{}", JSON.toJSONString(precreateRequest));

        //扫码支付使用execute方法
        AlipayTradePrecreateResponse response = alipayClient.execute(precreateRequest);
        log.info("AlipayTradePrecreateResponse = {}", response.getBody());
        return response;
    }

    private AlipayClient getAlipayClient() {
        String serverUrl = alipayConfig.getGatewayUrl();
        String appId = alipayConfig.getAppId();
        String privateKey = alipayConfig.getPrivateKey();
        String format = "json";
        String charset = alipayConfig.getCharset();
        String alipayPublicKey = alipayConfig.getPublicKey();
        String signType = alipayConfig.getSignType();
        return new DefaultAlipayClient(serverUrl, appId, privateKey, format, charset, alipayPublicKey, signType);
    }
}
