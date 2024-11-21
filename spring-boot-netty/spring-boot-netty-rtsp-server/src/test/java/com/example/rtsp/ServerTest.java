package com.example.rtsp;

import com.example.rtsp.server.NettyServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.rtsp.RtspDecoder;
import io.netty.handler.codec.rtsp.RtspEncoder;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author fzy
 * @description:
 * @date 2024/11/20 10:26
 */
public class ServerTest {
    public static void main(String[] args) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<>() {
                @Override
                public void initChannel(Channel ch) {
                    ch.pipeline().addLast(new LoggingHandler())
                            .addLast(new HttpServerCodec())
                            .addLast(new HttpObjectAggregator(65536))
                            .addLast(new RtspDecoder()).addLast(new RtspEncoder())
                            .addLast(new NettyServerHandler());
                }
            });
            Channel ch = b.bind(8555).sync().channel();
            System.out.println("RTSP server started at " + ch.localAddress());
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
