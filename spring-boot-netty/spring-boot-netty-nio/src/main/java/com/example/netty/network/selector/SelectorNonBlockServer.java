package com.example.netty.network.selector;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @description: selector-非阻塞-服务端
 * @Date 2023/3/7 9:50
 * @Author fzy
 */
@Slf4j
public class SelectorNonBlockServer {

    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();

        ServerSocketChannel ssChannel = ServerSocketChannel.open();
        ssChannel.configureBlocking(false);
        //注册连接事件
        ssChannel.register(selector, SelectionKey.OP_ACCEPT);

        ServerSocket serverSocket = ssChannel.socket();
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 8080);
        serverSocket.bind(address);

        while (true) {
            //使用 select() 来监听到达的事件，它会一直阻塞直到有至少一个事件到达。
            selector.select();

            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = keys.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                if (key.isAcceptable()) {
                    //除了下面写法，也可以使用上面服务端全局ssChannel进行accept,SocketChannel channel = ssChannel.accept()
                    ServerSocketChannel ssChannel1 = (ServerSocketChannel) key.channel();
                    // 服务器会为每个新连接创建一个 SocketChannel，accept获取的sChannel是对应客户端的
                    SocketChannel sChannel = ssChannel1.accept();
                    sChannel.configureBlocking(false);
                    // 这个新连接主要用于从客户端读取数据
                    sChannel.register(selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    //通过key.channel() 获取的是客户端的channel
                    SocketChannel sChannel = (SocketChannel) key.channel();
                    System.out.println(readDataFromSocketChannel(sChannel));
                    sChannel.close();
                }
                //每处理完一个事件都要进行remove移除
                keyIterator.remove();
            }
        }
    }

    /**
     * 读取channel中的数据
     *
     * @param sChannel SocketChannel
     * @return channel中的内容
     */
    private static String readDataFromSocketChannel(SocketChannel sChannel) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        StringBuilder data = new StringBuilder();

        while (true) {
            //先清空后读取
            buffer.clear();
            int n = sChannel.read(buffer);
            if (n == -1) {
                break;
            }
            buffer.flip();
            //获取buffer中数据长度保存在dst中
            int limit = buffer.limit();
            char[] dst = new char[limit];
            for (int i = 0; i < limit; i++) {
                dst[i] = (char) buffer.get(i);
            }
            data.append(dst);
            buffer.clear();
        }
        return data.toString();
    }

    /**
     * 简单案例
     */
    public static void simpleMain() {
        //缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(16);
        //获取服务器通道
        try (ServerSocketChannel serverChannel = ServerSocketChannel.open()) {
            //为服务器通道绑定端口
            serverChannel.bind(new InetSocketAddress(8080));
            //###创建选择器
            Selector selector = Selector.open();
            //设置非阻塞模式
            serverChannel.configureBlocking(false);
            //将通道注册到选择其中，并设置感兴趣的事件,此处关注连接事件，多个事件使用'|'符号分隔
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {
                //若没有事件就绪，线程会被阻塞，反之不会被阻塞，避免了CPU空转。
                //返回值为就绪的事件个数
                int ready = selector.select();
                log.info("选择器就绪的事件的数量:{}", ready);
                //获取所有事件
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                //使用迭代器遍历事件
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    //获取key
                    SelectionKey key = iterator.next();
                    //如果是连接事件
                    if (key.isAcceptable()) {
                        log.info("处理连接事件...");
                        ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                        SocketChannel accept = channel.accept();
                        log.info("连接成功...{}", accept);
                        iterator.remove();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
