package com.example.netty.udp.multicast.test;

import com.example.netty.udp.multicast.NetUtils;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;

/**
 * @author fzy
 * @description: 来源newBing
 * 出现的问题：IPv6 socket cannot join IPv4 multicast group
 * @date 2023/10/30 9:40
 */
public class Test01 {

    // 定义一个端口号
    private static final int PORT = 7574;

    // 定义一个组播地址
    private static final String GROUP_ADDRESS = "225.25.25.25";

    public void run() throws Exception {
        NetworkInterface networkInterface = NetUtils.getNetworkInterfaceByName("eth2");
        // 创建一个事件循环组
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            // 创建一个引导对象
            Bootstrap b = new Bootstrap();
            // 设置事件循环组，通道类型，初始化器
            b.group(group)
                    .channel(NioDatagramChannel.class)
                    .handler(new ChannelInitializer<NioDatagramChannel>() {
                        @Override
                        protected void initChannel(NioDatagramChannel ch) {
                            // 添加一个处理器，用于接收和发送数据包
                            ch.pipeline().addLast(new UDPMulticastServerHandler());
                        }
                    });
            // 绑定端口号，同步等待结果
            ChannelFuture f = b.bind(PORT).sync();
            // 获取通道
            NioDatagramChannel channel = (NioDatagramChannel) f.channel();
            // 创建一个组播地址对象
            InetAddress groupAddress = InetAddress.getByName(GROUP_ADDRESS);
            // 加入组播组
            InetSocketAddress inetSocketAddress = new InetSocketAddress(groupAddress, PORT);
            channel.joinGroup(inetSocketAddress, networkInterface).sync();
            // 等待通道关闭
            channel.closeFuture().await();
        } finally {
            // 释放资源
            group.shutdownGracefully();
        }
    }

    public static class UDPMulticastServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {
        @Override
        protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket packet) throws Exception {
            // 获取数据包中的内容
            ByteBuf buf = packet.content();
            // 转换为字节数组
            byte[] data = new byte[buf.readableBytes()];
            buf.readBytes(data);
            // 转换为字符串
            String msg = new String(data);
            // 打印接收到的消息和发送者的地址
            System.out.println("Received: " + msg + " from " + packet.sender());
        }
    }

    public static void main(String[] args) throws Exception {
        Test01 server = new Test01();
        server.run();
    }
}
