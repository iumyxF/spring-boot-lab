package com.example.netty.client.controller;

import com.alibaba.fastjson2.JSON;
import com.example.netty.client.message.auth.AuthRequest;
import com.example.netty.client.service.NettyClient;
import com.example.netty.common.codec.Invocation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description:
 * @Date 2023/3/15 10:31
 * @Author fzy
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class ClientController {

    @Resource
    private NettyClient nettyClient;

    @PostMapping("/mock")
    public String mock(String type, String message) {
        nettyClient.send(new Invocation(type, message));
        return "sent successfully";
    }

    /**
     * 登录
     */
    @GetMapping("/auth")
    public String auth() {
        AuthRequest request = new AuthRequest();
        request.setAccessToken("iumyxF");
        Invocation invocation = new Invocation(AuthRequest.TYPE, JSON.toJSONString(request));
        nettyClient.send(invocation);
        return "sent successfully";
    }

}
