package com.example.netty.network.selector;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * @description: 客户端
 * @Date 2023/3/7 9:58
 * @Author fzy
 */
@Slf4j
public class SelectorClient {
    public static void main(String[] args) {
        //获取客户端channel
        try (SocketChannel socketChannel = SocketChannel.open()) {
            //绑定服务端地址和端口
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 8080));
            socketChannel.write(ByteBuffer.wrap("hello world".getBytes(StandardCharsets.UTF_8)));
            socketChannel.close();
            log.info("发送消息...");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
