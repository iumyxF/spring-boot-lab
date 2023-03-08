package com.example.sign.controller;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.example.sign.utils.AccessUtils;
import org.junit.jupiter.api.Test;

import java.util.HashMap;


/**
 * @description:
 * @Date 2023/3/8 15:01
 * @Author fzy
 */
class NameControllerTest {

    /**
     * 使用http访问接口
     */
    @Test
    void randomName() {
        String url = "http://127.0.0.1:8080/name/";
        String appKey = "abc";
        String timeStamp = String.valueOf(System.currentTimeMillis() / 1000);
        String nonce = RandomUtil.randomNumbers(3);
        String sign = AccessUtils.generateSign(appKey, timeStamp, "123");
        System.out.println("客户端生成的sign = " + sign);
        HashMap<String, String> headers = new HashMap<>();
        headers.put("app_key", appKey);
        headers.put("time_stamp", timeStamp);
        headers.put("nonce", nonce);
        headers.put("sign", sign);
        HttpResponse response = HttpRequest.get(url)
                .addHeaders(headers)
                .timeout(20000)//超时，毫秒
                .execute();
        String body = response.body();
        System.out.println(body);
    }
}