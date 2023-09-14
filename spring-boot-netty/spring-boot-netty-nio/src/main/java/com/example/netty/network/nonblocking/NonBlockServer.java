package com.example.netty.network.nonblocking;


import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

/**
 * @description: 非阻塞-服务端
 * @Date 2023/3/7 9:50
 * @author iumyxF
 */
@Slf4j
public class NonBlockServer {

    /**
     * 服务端不会阻塞在accept操作了，但是线程大部分时间都在做无用功
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
                //###设置非阻塞模式
                serverChannel.configureBlocking(false);
                //没有连接时，此处会阻塞等待
                SocketChannel socketChannel = serverChannel.accept();
                //###当socketChannel不为空才放入集合
                if (socketChannel != null) {
                    log.info("有用户进行了连接...");
                    channels.add(socketChannel);
                }
                //循环遍历用户channel集合
                for (SocketChannel channel : channels) {
                    //处理通道中的数据，设置为非阻塞模式，若通道中没有数据，会返回0，不会阻塞线程
                    channel.configureBlocking(false);
                    int read = channel.read(buffer);
                    if (read > 0) {
                        log.info("读取用户channel内容...");
                        buffer.flip();
                        buffer.clear();
                        log.info("读取channel数据完毕");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
