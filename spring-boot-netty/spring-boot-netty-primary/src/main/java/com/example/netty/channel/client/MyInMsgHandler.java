package com.example.netty.channel.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description: 客户端接受消息的channel
 * @Date 2023/3/10 13:55
 * @author iumyxF
 */
public class MyInMsgHandler extends ChannelInboundHandlerAdapter {
    /**
     * 当客户端主动连接服务端后，通道就会变成Active装填，代表客户端和服务端连接完成，可以进行数据传输
     *
     * @param ctx channel上下文
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        SocketChannel channel = (SocketChannel) ctx.channel();
        System.out.println("链接报告开始");
        System.out.println("链接报告信息：本客户端连接到服务端。channelId：" + channel.id());
        System.out.println("链接报告IP:" + channel.localAddress().getHostString());
        System.out.println("链接报告Port:" + channel.localAddress().getPort());
        System.out.println("链接报告完毕");
        //通知客户端链接建立成功
        String str = "通知服务端链接建立成功" + " " + new Date() + " " + channel.localAddress().getHostString() + "\r\n";
        ctx.writeAndFlush(str);
    }

    /**
     * 当客户端主动断开服务端的链接后，这个通道就是不活跃的。也就是说客户端与服务端的关闭了通信通道并且不可以传输数据
     *
     * @param ctx 上下文
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        System.out.println("断开链接" + ctx.channel().localAddress().toString());
    }

    /**
     * 服务端发送消息给客户端，此时客户端需要读取channel中的消息
     *
     * @param ctx 上下文
     * @param msg 消息
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " 客户端接收到消息：" + msg);
        //通知客户端链消息发送成功
        String str = "随机数：" + Math.random() * 10 + "\r\n";
        ctx.writeAndFlush(str);
    }

    /**
     * 发生异常
     *
     * @param ctx   上下文
     * @param cause 异常原因
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
        System.out.println("异常信息：\r\n" + cause.getMessage());
    }
}
