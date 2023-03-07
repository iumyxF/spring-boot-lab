package com.example.netty.network.block;


import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

/**
 * @description: 阻塞-服务端
 * @Date 2023/3/7 9:50
 * @Author fzy
 */
@Slf4j
public class BlockServer {

    /**
     * 服务端会不断阻塞在accept()操作
     */
    public static void main(String[] args) {
        //缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(16);
        //获取服务器通道
        try (ServerSocketChannel serverChannel = ServerSocketChannel.open()) {
            //为服务器通道绑定端口
            serverChannel.bind(new InetSocketAddress(8080));
            //用户存放连接的集合
            ArrayList<SocketChannel> channels = new ArrayList<>();
            //循环接收连接
            while (true) {
                log.info("等待连接中...");
                //没有连接时，此处会阻塞等待
                SocketChannel socketChannel = serverChannel.accept();
                log.info("有用户进行了连接...");
                //添加到用户集合中
                channels.add(socketChannel);
                //循环遍历用户channel集合
                for (SocketChannel channel : channels) {
                    log.info("读取用户channel内容...");
                    channel.read(buffer);
                    buffer.flip();
                    buffer.clear();
                    log.info("读取channel数据完毕");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
