package com.example.sign.annotation;

import java.lang.annotation.*;

/**
 * @description: 校验签名注解
 * @Date 2023/3/8 15:11
 * @author iumyxF
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CheckSignature {

}
