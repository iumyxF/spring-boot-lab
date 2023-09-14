package com.example.netty.http.web;

import com.example.netty.http.server.NettyServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description:
 * @Date 2023/3/9 14:58
 * @author iumyxF
 */
@RestController
@RequestMapping(value = "/server")
public class NettyController {

    @Resource
    private NettyServer nettyServer;

    @GetMapping("/localAddress")
    public String localAddress() {
        return "nettyServer localAddress " + nettyServer.getChannel().localAddress();
    }

    @GetMapping("/isOpen")
    public String isOpen() {
        return "nettyServer isOpen " + nettyServer.getChannel().isOpen();
    }

}

