package com.example.language.config;

import cn.hutool.core.util.StrUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @author feng
 * @date 2023/2/9 20:26
 */
@Configuration
public class I18nConfig {

    @Bean
    public LocaleResolver localeResolver() {
        return new I18nLocaleResolver();
    }

    /**
     * 处理请求头国际化信息
     */
    static class I18nLocaleResolver implements LocaleResolver {

        @Override
        public Locale resolveLocale(HttpServletRequest request) {
            String language = request.getHeader("content-language");
            Locale locale = Locale.getDefault();
            if (StrUtil.isNotBlank(language)) {
                String[] split = language.split("_");
                locale = new Locale(split[0], split[1]);
            }
            return locale;
        }

        @Override
        public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

        }
    }
}
