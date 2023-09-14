package com.example.netty.demo.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.util.CharsetUtil;

/**
 * @description: 服务端handler处理
 * @Date 2023/3/9 10:25
 * @author iumyxF
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    /**
     * 读取客户端发送过来的消息
     *
     * @param ctx 上下文对象，含有 管道pipeline，通道channel，地址
     * @param msg 就是客户端发送的数据，默认Object
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println("[服务端] : 读取线程是: " + Thread.currentThread().getName());
        System.out.println("[服务端] : 上下文对象 ctx = " + ctx);
        //看看Channel和Pipeline的关系
        Channel channel = ctx.channel();
        //本质是个双向链表，出栈入栈
        ChannelPipeline pipeline = ctx.pipeline();
        //将msg转成一个ByteBuf，比NIO的ByteBuffer性能更高
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("[服务端] : 客户端发送的消息是：" + buf.toString(CharsetUtil.UTF_8));
        System.out.println("[服务端] : 客户端地址：" + ctx.channel().remoteAddress());
    }

    /**
     * 数据读取完毕
     *
     * @param ctx 上下文对象，含有 管道pipeline，通道channel，地址
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        //它是 write + flush，将数据写入到缓存buffer，并将buffer中的数据flush进通道
        //一般讲，我们对这个发送的数据进行编码
        ctx.writeAndFlush(Unpooled.copiedBuffer("[服务端] : hello, 客户端~ , i am server", CharsetUtil.UTF_8));
    }

    /**
     * 处理异常，一般是关闭通道
     *
     * @param ctx   上下文channel
     * @param cause 异常信息
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }
}