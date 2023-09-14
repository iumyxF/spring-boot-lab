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
 * @author iumyxF
 */
@Slf4j
public class SelectorClient {
    public static void main(String[] args) {
        String message = "123456789101112131415161718192021222324252627282930313233343536373839404142434445464748495051525354555657585960over";
        //获取客户端channel
        try (SocketChannel socketChannel = SocketChannel.open()) {
            //绑定服务端地址和端口
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 8080));
            socketChannel.write(ByteBuffer.wrap(message.getBytes(StandardCharsets.UTF_8)));
            socketChannel.close();
            log.info("发送消息...");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
