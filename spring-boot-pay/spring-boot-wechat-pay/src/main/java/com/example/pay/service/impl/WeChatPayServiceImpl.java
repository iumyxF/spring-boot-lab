package com.example.pay.service.impl;

import com.example.pay.model.PaymentInfoForm;
import com.example.pay.model.WxPrepayVo;
import com.example.pay.service.WeChatPayService;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author fzy
 * @description:
 * @date 2024/9/21 14:11
 */
@Slf4j
@Service
public class WeChatPayServiceImpl implements WeChatPayService {

    @Resource
    private WxPayService wxPayService;

    @Override
    public WxPrepayVo createWxPayment(PaymentInfoForm paymentInfoForm, String spbillCreateIp) {
        // 系统内部订单记录的处理...略

        // 创建微信支付订单
        WxPayUnifiedOrderRequest wxPayUnifiedOrderRequest = WxPayUnifiedOrderRequest.newBuilder()
                // 交易类型
                .tradeType(WxPayConstants.TradeType.JSAPI)
                // 终端IP
                .spbillCreateIp(spbillCreateIp)
                // 调起支付的人的 openId
                .openid(paymentInfoForm.getCustomerOpenId())
                // 订单编号
                .outTradeNo(paymentInfoForm.getOrderNo())
                // 订单金额
                .totalFee(1)
                // 商品描述
                .body(paymentInfoForm.getContent())
                // 分账信息(没有权限暂时开不了)
                //.profitSharing("Y")
                .build();
        try {
            WxPayMpOrderResult orderResult = wxPayService.createOrder(wxPayUnifiedOrderRequest);
            WxPrepayVo wxPrepayVo = new WxPrepayVo();
            wxPrepayVo.setAppId(orderResult.getAppId());
            wxPrepayVo.setTimeStamp(orderResult.getTimeStamp());
            wxPrepayVo.setNonceStr(orderResult.getNonceStr());
            wxPrepayVo.setPackageVal(orderResult.getPackageValue());
            wxPrepayVo.setSignType(orderResult.getSignType());
            wxPrepayVo.setPaySign(orderResult.getPaySign());
            return wxPrepayVo;
        } catch (WxPayException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public void parseOrderNotifyResult(String xmlData) {
        try {
            // 校验结果
            WxPayOrderNotifyResult wxPayOrderNotifyResult = wxPayService.parseOrderNotifyResult(xmlData);
            wxPayOrderNotifyResult.checkResult(wxPayService, "MD5", true);

            // 支付成功处理...
        } catch (WxPayException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
