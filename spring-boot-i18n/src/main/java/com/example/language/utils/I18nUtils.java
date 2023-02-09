package com.example.language.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * @author feng
 * @date 2023/2/9 20:42
 */
@Component
@Slf4j
public class I18nUtils {

    /**
     * 如果当前bean不加@Component注解，则messageSource无法注入，始终为null
     */

    private static MessageSource messageSource;

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        I18nUtils.messageSource = messageSource;
    }

    /**
     * 解析code对应的信息进行返回，如果对应的code不能被解析则抛出异常NoSuchMessageException
     *
     * @param code 需要进行解析的code，对应资源文件中的一个属性名
     * @param args 当对应code对应的信息不存在时需要返回的默认值
     * @return 国际化翻译值
     */
    public static String i18n(String code, Object... args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }

    /**
     * 解析code对应的信息进行返回，如果对应的code不能被解析则返回默认信息defaultMessage。
     *
     * @param code           需要进行解析的code，对应资源文件中的一个属性名
     * @param defaultMessage 当对应code对应的信息不存在时需要返回的默认值
     * @param args           需要用来替换code对应的信息中包含参数的内容，如：{0},{1,date},{2,time}
     * @return 对应的Locale
     */
    public static String i18nOrDefault(String code, String defaultMessage, Object... args) {
        return messageSource.getMessage(code, args, defaultMessage, LocaleContextHolder.getLocale());
    }

    /**
     * 因为i18n方法如果获取不到对应的键值，会抛异常NoSuchMessageException
     * 本方法是对i18n方法的封装。当报错时并不抛出异常，而是返回source
     *
     * @param source 模板
     * @param args   参数
     * @return 返回I18n（正常结束）或者source（抛出异常）
     * @see #i18n(String, Object...)
     */
    @NonNull
    public static String tryI18n(@NonNull String source, @NonNull Object... args) {
        String res;
        try {
            res = i18n(source, args);
        } catch (Exception ignored) {
            res = source;
        }
        return res;
    }
}

