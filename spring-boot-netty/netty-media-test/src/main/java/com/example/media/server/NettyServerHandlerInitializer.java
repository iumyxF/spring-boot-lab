package com.example.media.server;

import com.example.media.codec.TcpDecoder;
import com.example.media.codec.TcpEncoder;
import com.example.media.handler.DispatchHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author feng
 * @description:
 * @date 2024/11/13 20:56
 */
@Component
public class NettyServerHandlerInitializer extends ChannelInitializer<SocketChannel> {
    /**
     * 心跳超时时间
     */
    private static final Integer READ_TIMEOUT_SECONDS = 3 * 60;

    @Resource
    private DispatchHandler dispatchHandler;

    @Override
    protected void initChannel(SocketChannel channel) {
        channel.pipeline().addLast(new IdleStateHandler(true, 0, 0, 0, TimeUnit.SECONDS));
        channel.pipeline().addLast(new ReadTimeoutHandler(READ_TIMEOUT_SECONDS, TimeUnit.SECONDS));
        channel.pipeline().addLast(new ChunkedWriteHandler());
        // 编码和解码
        channel.pipeline().addLast(new TcpDecoder());
        channel.pipeline().addLast(new TcpEncoder());
        // 消息分发处理器
        channel.pipeline().addLast(dispatchHandler);
    }
}
