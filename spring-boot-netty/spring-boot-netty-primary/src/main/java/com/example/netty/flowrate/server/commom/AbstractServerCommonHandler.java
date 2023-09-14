package com.example.netty.flowrate.server.commom;


import io.netty.channel.*;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;

/**
 * @description:
 * @Date 2023/3/27 16:43
 * @author iumyxF
 */
public abstract class AbstractServerCommonHandler extends SimpleChannelInboundHandler<String> {

    protected boolean sentFlag;
    private Runnable counterTask;
    private AtomicLong consumeMsgLength = new AtomicLong();
    private long priorProgress;

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        counterTask = () -> {
            while (true) {
                try {
                    Thread.sleep(500);
                    long length = consumeMsgLength.getAndSet(0);
                    if (0 == length) {
                        continue;
                    }
                    System.out.println("数据发送速率(KB/S)：" + length);
                } catch (InterruptedException ignored) {
                }
            }
        };
        super.handlerAdded(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        sendData(ctx);
        //启动监控线程
        new Thread(counterTask).start();
    }

    /**
     * 发送数据
     * @param ctx
     */
    protected abstract void sendData(ChannelHandlerContext ctx);

    protected ChannelProgressivePromise getChannelProgressivePromise(ChannelHandlerContext ctx, Consumer<ChannelProgressiveFuture> completedAction) {
        ChannelProgressivePromise channelProgressivePromise = ctx.newProgressivePromise();
        channelProgressivePromise.addListener(new ChannelProgressiveFutureListener() {
            @Override
            public void operationProgressed(ChannelProgressiveFuture future, long progress, long total) {
                consumeMsgLength.addAndGet(progress - priorProgress);
                priorProgress = progress;
            }

            @Override
            public void operationComplete(ChannelProgressiveFuture future) {
                sentFlag = false;
                if (future.isSuccess()) {
                    System.out.println("消息发送成功！");
                    priorProgress -= 10;
                    Optional.ofNullable(completedAction).ifPresent(action -> action.accept(future));
                } else {
                    System.out.println("消息发送失败！");
                    future.cause().printStackTrace();
                }
            }
        });
        return channelProgressivePromise;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) {
        System.out.println("NettyServer接收到消息：" + msg);
    }
}
