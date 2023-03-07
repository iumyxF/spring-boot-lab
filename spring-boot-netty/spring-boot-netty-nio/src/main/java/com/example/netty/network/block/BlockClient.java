package com.example.netty.network.block;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

/**
 * @description: 阻塞 客户端
 * @Date 2023/3/7 9:58
 * @Author fzy
 */
@Slf4j
public class BlockClient {
    public static void main(String[] args) {
        //获取客户端channel
        try (SocketChannel socketChannel = SocketChannel.open()) {
            //绑定服务端地址和端口
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 8080));
            log.info("等待服务器连接...");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
