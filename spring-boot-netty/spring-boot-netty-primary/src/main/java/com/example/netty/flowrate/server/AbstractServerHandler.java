package com.example.netty.flowrate.server;


import com.example.netty.flowrate.server.commom.AbstractServerCommonHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelProgressiveFuture;

import java.util.function.Consumer;

/**
 * @description:
 * @Date 2023/3/27 16:43
 * @author iumyxF
 */
public class AbstractServerHandler extends AbstractServerCommonHandler {

    @Override
    protected void sendData(ChannelHandlerContext ctx) {
        sentFlag = true;
        ctx.writeAndFlush("111111111122222222223333333333\r\n", getChannelProgressivePromise(ctx, channelProgressiveFuture -> {
            if (ctx.channel().isWritable() && !sentFlag) {
                sendData(ctx);
            }
        }));
    }
}
