package com.example.language.controller;

import com.example.language.entities.Result;
import com.example.language.exception.CommonException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Locale;


/**
 * @author feng
 * @date 2023/2/9 20:35
 */
@RestController
@RequestMapping("/i18n")
public class TestController {

    @Resource
    private MessageSource messageSource;

    @GetMapping("/test1")
    public String test1(String message) {
        return messageSource.getMessage(message, new Object[]{}, LocaleContextHolder.getLocale());
    }

    @GetMapping("/test2")
    public Result test2() {
        int i = 0;
        try {
            i = i / 0;
            System.out.println(i);
        } catch (RuntimeException e) {
            throw new CommonException("action.fail");
        }
        return Result.ok();
    }

    @GetMapping("/test3")
    public Result test3() {
        throw new CommonException("user.register.fail", "jack");
    }

    /**
     * 不依赖请求头的获取多语言
     */
    @GetMapping("/test4")
    public Result<String> test4(@RequestParam String message,
                                @RequestParam String language) {
        Locale locale = new Locale(language);
        return Result.ok(messageSource.getMessage(message, null, locale));
    }

    /**
     * 不依赖请求头的获取有参数的多语言
     */
    @GetMapping("/test5")
    public Result<String> test5(@RequestParam String language) {
        Locale locale = new Locale(language);
        Object[] args = new Object[]{"jack"};
        String message = messageSource.getMessage("user.register.fail", args, locale);
        return Result.ok(message);
    }

}
