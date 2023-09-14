package com.example.aop.annotation;

import java.lang.annotation.*;

/**
 * @description: 定义一个普通注解
 * @Date 2023/2/13 10:27
 * @author iumyxF
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface HunterLogAnno {
    String value() default "";
}
