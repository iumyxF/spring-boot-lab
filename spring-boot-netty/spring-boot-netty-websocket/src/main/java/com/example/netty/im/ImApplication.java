package com.example.netty.im;

import com.example.netty.im.server.NettyServer;
import io.netty.channel.ChannelFuture;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;
import java.net.InetSocketAddress;

/**
 * @description: 启动类
 * @Date 2023/3/13 14:11
 * @author iumyxF
 */
@SpringBootApplication
public class ImApplication implements CommandLineRunner {

    @Value("${netty.host}")
    private String host;

    @Value("${netty.port}")
    private int port;

    @Resource
    private NettyServer nettyServer;

    public static void main(String[] args) {
        SpringApplication.run(ImApplication.class, args);
    }

    @Override
    public void run(String... args) {
        InetSocketAddress address = new InetSocketAddress(host, port);
        //启动nettyServer
        ChannelFuture channelFuture = nettyServer.bing(address);
        //监听spring关闭，关闭nettyServer
        Runtime.getRuntime().addShutdownHook(new Thread(() -> nettyServer.destroy()));
        channelFuture.channel().closeFuture().syncUninterruptibly();
    }
}
