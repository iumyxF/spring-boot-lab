package com.example.dwz.controller;

import com.example.dwz.utils.UrlUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author iumyxF
 * @description:
 * @date 2024/4/17 15:03
 */
@Controller
@RequestMapping("/dwz")
public class DwzController {

    private static final Map<String, String> codeUrlMap = new HashMap<>();

    private static final String BASE_URL = "http://localhost:18080/dwz/redirect";

    /**
     * 根据rul获取短链码
     *
     * @param url 源url
     * @return
     */
    @GetMapping("/code")
    public ResponseEntity<String> get(@RequestParam("url") String url) {
        String code = UrlUtils.createShortUrl(url);
        codeUrlMap.put(code, url);
        return ResponseEntity.ok(code);
    }

    /**
     * 302 跳转
     * curl -i http://localhost:18080/dwz/redirect?url=https://myShort.com/967cfb2b
     *
     * @param code 短链码
     * @return
     */
    @GetMapping("/redirect")
    public RedirectView redirect(@RequestParam(value = "code") String code) {
        //找到该code对应的url
        String url = codeUrlMap.get(code);
        System.out.println(url);
        //测试使用
        String testUrl = "https://kaifa.baidu.com/";
        //读取持久层获取原来的连接并跳转
        return new RedirectView(testUrl);
    }
}
