package com.example.netty.demo.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @description: 服务端
 * @Date 2023/3/9 10:24
 * @author iumyxF
 */
public class NettyServer {
    public static void main(String[] args) throws InterruptedException {
        //创建BossGroup 和 WorkerGroup
        //1、创建两个线程组，bossGroup 和 workerGroup
        //2、bossGroup 只是处理连接请求，真正的和客户端业务处理，会交给 workerGroup 完成
        //3、两个都是无限循环
        //4、bossGroup 和 workerGroup 含有的子线程（NioEventLoop）个数为实际 cpu 核数 * 2
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            //创建服务器端的启动对象，配置参数
            ServerBootstrap bootstrap = new ServerBootstrap();

            //使用链式编程来进行设置，配置
            //设置两个线程组
            bootstrap.group(bossGroup, workerGroup)
                    //使用 NioServerSocketChannel 作为服务器的通道实现
                    .channel(NioServerSocketChannel.class)
                    //设置线程队列得到连接个数
                    .option(ChannelOption.SO_BACKLOG, 128)
                    //设置保持活动连接状态
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    //为accept channel的pipeline预添加的handler
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        //给 pipeline 添加处理器，每当有连接accept时，就会运行到此处。
                        @Override
                        protected void initChannel(SocketChannel socketChannel) {
                            socketChannel.pipeline().addLast(new NettyServerHandler());
                        }
                    }); //给我们的 workerGroup 的 EventLoop 对应的管道设置处理器

            System.out.println("........服务器 is ready...");
            //绑定一个端口并且同步，生成了一个ChannelFuture 对象
            //启动服务器（并绑定端口）
            ChannelFuture future = bootstrap.bind(6666).sync();

            //对关闭通道进行监听
            future.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}

