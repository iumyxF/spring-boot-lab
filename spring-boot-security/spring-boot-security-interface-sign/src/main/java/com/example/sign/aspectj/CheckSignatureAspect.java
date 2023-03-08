package com.example.sign.aspectj;

import com.example.sign.annotation.CheckSignature;
import com.example.sign.utils.AccessUtils;
import com.example.sign.utils.ServletUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @description: 校验签名
 * @Date 2023/3/8 15:14
 * @Author fzy
 */
@Slf4j
@Aspect
@Component
public class CheckSignatureAspect {

    @Resource
    AccessUtils accessUtils;

    @Before("@annotation(checkSignature)")
    public void check(JoinPoint joinPoint, CheckSignature checkSignature) {
        HttpServletRequest request = ServletUtils.getRequest();
        boolean verification = accessUtils.signatureVerification(request);
        if (!verification) {
            log.error("权限不通过");
            return;
        }
        System.out.println("可以访问");
    }
}
