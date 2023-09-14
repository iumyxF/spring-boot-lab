package com.example.language.controller;

import com.example.language.entities.Result;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @description: 校验测试
 * @Date 2023/2/17 14:46
 * @author iumyxF
 */
@Validated
@RestController
@RequestMapping("/demo/i18n")
public class ValidatorController {

    /**
     * Validator 校验国际化
     * 不传值 分别查看异常返回
     * <p>
     * 测试使用 not.null
     */
    @GetMapping("/test1")
    public Result<Void> test1(@NotBlank(message = "{not.null}") String str) {
        return Result.ok(str);
    }

    /**
     * Bean 校验国际化
     * 不传值 分别查看异常返回
     * <p>
     * 测试使用 not.null
     */
    @GetMapping("/test2")
    public Result<TestI18nBo> test2(@Validated TestI18nBo bo) {
        return Result.ok(bo);
    }

    @Data
    public static class TestI18nBo {

        @NotBlank(message = "name {not.null}")
        private String name;

        @NotNull(message = "{not.null}")
        @Range(min = 0, max = 100, message = "age {length.not.valid}")
        private Integer age;
    }
}
