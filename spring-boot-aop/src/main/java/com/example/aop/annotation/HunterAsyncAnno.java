package com.example.aop.annotation;


import java.lang.annotation.*;

/**
 * @description: 异步执行方法的注解，他能及时响应一个结果，异步执行标注了该注解的方法体
 * 感觉可以使用在创建订单上，发送订单创建后，直接返回一个订单号，后台再异步处理创建订单的内容
 * @Date 2023/2/13 13:59
 * @Author fzy
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface HunterAsyncAnno {
    /**
     * 核心线程数
     */
    int coreSize() default 1;

    /**
     * 最大核心线程数
     */
    int maxSize() default 1;

}
