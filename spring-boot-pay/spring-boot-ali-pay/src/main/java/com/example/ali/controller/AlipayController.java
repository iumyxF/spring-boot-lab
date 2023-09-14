package com.example.ali.controller;

import com.alibaba.fastjson2.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.example.ali.entity.AlipayEntity;
import com.example.ali.service.impl.Alipay;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Random;
import java.util.UUID;

/**
 * @author iumyxF
 * @description:
 * @date 2023/6/26 9:29
 */
@Slf4j
@RestController
@RequestMapping
public class AlipayController {

    @Resource
    private Alipay alipay;

    @PostMapping("/alipay/pc/pay")
    public String payByPc(AlipayEntity entity) throws AlipayApiException {
        //模拟数据
        entity.setOut_trade_no(UUID.randomUUID().toString().replaceAll("-", ""));
        entity.setSubject("订单名称");
        entity.setTotal_amount(String.valueOf(new Random().nextInt(100)));
        entity.setBody("商品描述");
        //支付调用
        String result = alipay.payByPc(entity);
        log.info("请求支付宝付款返回参数为:{}", result);
        return result;
    }

    @PostMapping("/alipay/mobile/pay")
    public String payByMobile(AlipayEntity entity) throws Exception {
        //接口模拟数据
        entity.setOut_trade_no("20230817010101003");
        entity.setSubject("订单名称");
        entity.setTotal_amount(String.valueOf(new Random().nextInt(100)));
        entity.setBody("商品描述");

        AlipayTradePrecreateResponse response = null;
        try {
            response = alipay.payByMobile(entity);
        } catch (AlipayApiException e) {
            throw new Exception(String.format("下单失败 错误代码:[%s], 错误信息:[%s]", e.getErrCode(), e.getErrMsg()));
        }
        if (!response.isSuccess()) {
            throw new Exception(String.format("下单失败 错误代码:[%s], 错误信息:[%s]", response.getCode(), response.getMsg()));
        }
        // 返回结果，主要是返回 qr_code，前端根据 qr_code 进行重定向或者生成二维码引导用户支付
        JSONObject jsonObject = new JSONObject();
        //支付宝响应的订单号
        String outTradeNo = response.getOutTradeNo();
        jsonObject.put("outTradeNo", outTradeNo);
        //二维码地址，页面使用二维码工具显示出来就可以了
        jsonObject.put("qrCode", response.getQrCode());
        return jsonObject.toString();
    }

    @GetMapping("/success")
    public String success() {
        return "交易成功！";
    }

    @GetMapping(value = "/index")
    public String payCoin() {
        return "index.html";
    }
}
