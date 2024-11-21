package com.example.rtsp.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.rtsp.RtspDecoder;
import io.netty.handler.codec.rtsp.RtspEncoder;
import io.netty.handler.logging.LoggingHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author iumyxF
 * @description: handler初始化器
 * @Date 2023/3/14 10:55
 */
@Component
public class NettyServerHandlerInitializer extends ChannelInitializer<Channel> {

    @Resource
    private NettyServerHandler serverHandler;

    @Override
    protected void initChannel(Channel ch) {
        ch.pipeline().addLast(new LoggingHandler())
                .addLast(new HttpServerCodec())
                .addLast(new HttpObjectAggregator(65536))
                .addLast(new RtspDecoder())
                .addLast(new RtspEncoder())
                .addLast(serverHandler);
    }
}
