package com.example.office.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author iumyxF
 * @description: thymeleaf 测试
 * @date 2023/12/13 15:54
 */
@Controller
@RequestMapping("/tml")
public class ThymeleafController {

    @GetMapping("/test1")
    public String test1(Model model) {
        model.addAttribute("hello", "hello welcome");
        return "templates_1";
    }
}
