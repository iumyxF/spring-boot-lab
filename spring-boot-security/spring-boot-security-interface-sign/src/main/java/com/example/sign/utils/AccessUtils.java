package com.example.sign.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.example.sign.mapper.AccessMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @description: 接口签名认证工具 应该改成AccessService
 * @Date 2023/3/8 14:48
 * @Author fzy
 */
@Slf4j
@Component
public class AccessUtils {

    @Resource
    private AccessMapper accessMapper;

    public boolean signatureVerification(HttpServletRequest request) {
        log.info("接口开始认证...");
        String appKey = request.getHeader("app_key");
        String timeStamp = request.getHeader("time_stamp");
        String nonce = request.getHeader("nonce");
        String sign = request.getHeader("sign");
        //非空判断
        if (!StrUtil.isAllNotBlank(appKey, timeStamp, sign, nonce)) {
            log.error("必要参数为空,无法访问...");
            return false;
        }
        //time_stamp 和 nonce 需要保存在redis，防止同一个链接不断请求

        //校验appKey合法性
        String secret = accessMapper.getSecretByKey(appKey);
        if (StrUtil.isBlank(secret)) {
            log.error("无效的appKey");
            return false;
        }
        //校验sign签名
        String serverSign = AccessUtils.generateSign(appKey, timeStamp, secret);
        if (!sign.equals(serverSign)) {
            log.error("签名认证失败");
            return false;
        }
        log.info("接口认证通过可以访问...");
        return true;
    }

    /**
     * 根据规则生成sign签名
     *
     * @param appKey
     * @param timeStamp
     * @param appSecret
     * @return
     */
    public static String generateSign(String appKey, String timeStamp, String appSecret) {
        String str = SecureUtil.md5(appKey + timeStamp + appSecret);
        return SecureUtil.md5(str + appSecret);
    }

}
