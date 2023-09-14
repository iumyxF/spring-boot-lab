package com.example.file;

import com.example.netty.file.server.NettyServer;

/**
 * @description: 服务端文件上传测试
 * @Date 2023/3/11 14:04
 * @author iumyxF
 */
public class NettyFileServerTest {
    public static void main(String[] args) {
        System.out.println("start server...");
        //启动服务
        new NettyServer().bing(7397);
    }
}
