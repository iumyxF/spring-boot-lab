package com.example.netty.client.controller;

import com.alibaba.fastjson2.JSON;
import com.example.netty.client.message.auth.AuthRequest;
import com.example.netty.client.message.group.ChatJoinGroupRequest;
import com.example.netty.client.message.group.ChatSendToGroupRequest;
import com.example.netty.client.service.NettyClient;
import com.example.netty.common.codec.Invocation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author iumyxF
 * @description:
 * @Date 2023/3/15 10:31
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

    @GetMapping("/join")
    public String join(@RequestParam("userName") String userName) {
        ChatJoinGroupRequest request = new ChatJoinGroupRequest();
        request.setMsgId(String.valueOf(System.currentTimeMillis()));
        request.setGroupName("DEFAULT");
        request.setUser(userName);
        Invocation invocation = new Invocation(ChatJoinGroupRequest.TYPE, JSON.toJSONString(request));
        nettyClient.send(invocation);
        return "success";
    }

    @GetMapping("/chat/group")
    public String chatGroup(@RequestParam("groupName") String groupName) {
        ChatSendToGroupRequest request = new ChatSendToGroupRequest();
        request.setMsgId(String.valueOf(System.currentTimeMillis()));
        request.setGroupName(groupName);
        request.setFromUser("admin");
        request.setContent("我是管理员");
        Invocation invocation = new Invocation(ChatSendToGroupRequest.TYPE, JSON.toJSONString(request));
        nettyClient.send(invocation);
        return "success";
    }
}
