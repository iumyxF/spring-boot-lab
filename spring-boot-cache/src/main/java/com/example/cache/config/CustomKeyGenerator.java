package com.example.cache.config;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

/**
 * @author fzy
 * @description: 自定义key规则生成器
 * @date 2023/5/2 11:39
 */
@Component
public class CustomKeyGenerator implements KeyGenerator {

    @Override
    public Object generate(Object target, Method method, Object... params) {
        return target.getClass().getSimpleName() + "_" + method.getName() + "_" + StringUtils.arrayToDelimitedString(params, "_");
    }
}

