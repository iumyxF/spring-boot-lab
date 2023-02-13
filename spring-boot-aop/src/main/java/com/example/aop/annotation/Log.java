package com.example.aop.annotation;

import java.lang.annotation.*;

/**
 * @description: 操作日志记录注解
 * @Date 2023/2/13 9:52
 * @Author fzy
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.PARAMETER})
public @interface Log {
    String title() default "";
}
