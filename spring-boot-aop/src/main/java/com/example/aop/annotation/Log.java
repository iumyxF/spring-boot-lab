package com.example.aop.annotation;

import java.lang.annotation.*;

/**
 * @author iumyxF
 * @description: 操作日志记录注解
 * @Date 2023/2/13 9:52
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.PARAMETER})
public @interface Log {
    String title() default "";

    /**
     * 测试EL表达式
     */
    String content() default "";
}
