package com.example.netty.media.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author iumyxF
 * @description: netty 客户端
 * @Date 2023/3/9 10:27
 */
public class NettyClient {

    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static void main(String[] args) throws InterruptedException, JsonProcessingException {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();

            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) {
                            socketChannel.pipeline().addLast(new NettyClientHandler());
                        }
                    });

            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 1553).sync();
            channelFuture.channel().closeFuture().sync();

            String json = "{\"head\":\"iumyx\",\"device\":{\"user_message_id\":\"1\"},\"cmd\":{\"cmd\":\"reg\",\"respone\":0},\"args\":{\"group_name\":\"225.25.25.26:7575\"}}";
            try {
                byte[] dataBytes = OBJECT_MAPPER.writeValueAsBytes(json + "\n");
                channelFuture.channel().writeAndFlush(dataBytes);
                System.out.println("register send...");
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

        } finally {
            group.shutdownGracefully();
        }
    }
}
