package com.example.pay.controller;

import com.example.pay.model.PaymentInfoForm;
import com.example.pay.model.WxPrepayVo;
import com.example.pay.service.WeChatPayService;
import com.example.pay.utils.IpUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author fzy
 * @description:
 * @date 2024/9/21 14:05
 */
@RestController
@RequestMapping("/wechatPay")
public class WechatPayController {

    @Resource
    private WeChatPayService weChatPayService;

    /**
     * 创建微信支付
     *
     * @param paymentInfoForm 内部订单支付请求
     * @param request         请求
     * @return 支付信息
     */
    @PostMapping("/createWxPayment")
    public ResponseEntity<WxPrepayVo> createWxPayment(@RequestBody PaymentInfoForm paymentInfoForm,
                                                      HttpServletRequest request) {
        WxPrepayVo vo = weChatPayService.createWxPayment(paymentInfoForm, IpUtil.getIpAddress(request));
        return ResponseEntity.ok(vo);
    }

    /**
     * 支付回调通知处理
     *
     * @param xmlData 微信响应信息
     * @return 结果
     */
    @PostMapping("/notify/order")
    public ResponseEntity.BodyBuilder parseOrderNotifyResult(@RequestBody String xmlData) {
        weChatPayService.parseOrderNotifyResult(xmlData);
        return ResponseEntity.ok();
    }
}
