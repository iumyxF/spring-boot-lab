package com.example.language.exception;

import com.example.language.utils.I18nUtils;

/**
 * @author feng
 * @date 2023/2/9 20:43
 */
public class CommonException extends RuntimeException {

    public CommonException(String i18eCode) {
        // 根据资源文件的属性名以及当前语言环境，获取国际化信息
        super(I18nUtils.tryI18n(i18eCode));
    }

    public CommonException(String i18eCode, Object... args) {
        // 根据资源文件的属性名，属性值中的参数以及当前语言环境，获取国际化信息
        // args用来替换资源文件属性值中的占位符参数
        super(I18nUtils.tryI18n(i18eCode, args));
    }
}
