package com.example.language.controller;

import com.example.language.entities.Result;
import com.example.language.exception.CommonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author feng
 * @date 2023/2/9 20:35
 */
@RestController
@RequestMapping("/i18n")
public class TestController {

    @Autowired
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

}
