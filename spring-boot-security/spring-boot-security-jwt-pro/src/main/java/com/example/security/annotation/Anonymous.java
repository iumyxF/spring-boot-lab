package com.example.security.annotation;

import java.lang.annotation.*;

/**
 * @description: 允许匿名访问的接口
 * @Date 2023/2/22 9:44
 * @Author fzy
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Anonymous
{
}
