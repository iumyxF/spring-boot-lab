package com.example.netty.im.server;

import com.alibaba.fastjson.JSON;
import com.example.netty.im.domain.ClientMsgProtocol;
import com.example.netty.im.domain.MsgTypeConstants;
import com.example.netty.im.utils.MsgUtil;
import com.example.netty.im.utils.MyChannelHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.nio.charset.StandardCharsets;

/**
 * @description:
 * @Date 2023/3/13 14:43
 * @author iumyxF
 */
@Slf4j
@ChannelHandler.Sharable
public class MyServerHandler extends ChannelInboundHandlerAdapter {

    private WebSocketServerHandshaker handshaker;

    /**
     * 连接激活
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        SocketChannel channel = (SocketChannel) ctx.channel();
        log.info("链接报告开始");
        log.info("链接报告信息：有一客户端链接到本服务端");
        log.info("链接报告IP:{}", channel.localAddress().getHostString());
        log.info("链接报告Port:{}", channel.localAddress().getPort());
        log.info("链接报告完毕");
        MyChannelHandler.channelGroup.add(ctx.channel());
    }

    /**
     * 客户端申请断开连接
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        log.info("客户端断开链接{}", ctx.channel().localAddress().toString());
        MyChannelHandler.channelGroup.remove(ctx.channel());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //处理http请求
        if (msg instanceof FullHttpRequest) {
            log.info("服务端接受到http请求=>{}", msg);
            handlerHttpRequest(ctx, msg);
        }
        //websocket
        if (msg instanceof WebSocketFrame) {
            String message = ((TextWebSocketFrame) msg).text();
            log.info("服务端接受到websocket请求=>{}", message);
            handlerWebsocketRequest(ctx, msg);
        }
    }

    /**
     * 处理websocket请求
     */
    private void handlerWebsocketRequest(ChannelHandlerContext ctx, Object msg) throws Exception {

        WebSocketFrame webSocketFrame = (WebSocketFrame) msg;

        //关闭请求
        if (webSocketFrame instanceof CloseWebSocketFrame) {
            handshaker.close(ctx.channel(), (CloseWebSocketFrame) webSocketFrame.retain());
            return;
        }

        //ping请求
        if (webSocketFrame instanceof PingWebSocketFrame) {
            ctx.channel().write(new PongWebSocketFrame(webSocketFrame.content().retain()));
            return;
        }

        //只支持文本格式，不支持二进制（或其他）消息
        if (!(webSocketFrame instanceof TextWebSocketFrame)) {
            throw new Exception("仅支持文本格式");
        }

        String request = ((TextWebSocketFrame) webSocketFrame).text();
        System.out.println("服务端收到消息: " + request);

        ClientMsgProtocol clientMsgProtocol = JSON.parseObject(request, ClientMsgProtocol.class);

        //请求个人信息
        if (MsgTypeConstants.person == clientMsgProtocol.getType()) {
            //点对点
            ctx.channel().writeAndFlush(MsgUtil.buildMsgOwner(ctx.channel().id().toString()));
            return;
        }

        //群发消息
        if (MsgTypeConstants.chat == clientMsgProtocol.getType()) {
            TextWebSocketFrame textWebSocketFrame = MsgUtil.buildMsgAll(ctx.channel().id().toString(), clientMsgProtocol.getMsgInfo());
            //群发
            MyChannelHandler.channelGroup.writeAndFlush(textWebSocketFrame);
        }
    }

    /**
     * 处理HTTP请求
     */
    private void handlerHttpRequest(ChannelHandlerContext ctx, Object msg) {
        FullHttpRequest httpRequest = (FullHttpRequest) msg;
        //消息解码不成功
        if (!httpRequest.decoderResult().isSuccess()) {
            //响应失败消息
            DefaultFullHttpResponse httpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_0, HttpResponseStatus.BAD_REQUEST);
            if (httpResponse.status().code() != HttpStatus.OK.value()) {
                ByteBuf buffer = Unpooled.copiedBuffer(httpResponse.status().toString(), StandardCharsets.UTF_8);
                httpResponse.content().writeBytes(buffer);
                buffer.release();
            }
            //如果是非keep-alive，关闭连接
            ChannelFuture channelFuture = ctx.channel().writeAndFlush(httpResponse);
            if (httpResponse.status().code() != HttpStatus.OK.value()) {
                channelFuture.addListener(ChannelFutureListener.CLOSE);
            }
        }
        //处理http升级成ws
        WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory("ws:/" + ctx.channel() + "/websocket", null, false);
        handshaker = wsFactory.newHandshaker(httpRequest);

        // 请求头不合法, 导致handShaker没创建成功
        if (null == handshaker) {
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        } else {
            // 响应该请求
            handshaker.handshake(ctx.channel(), httpRequest);
        }
    }

    /**
     * 发生异常
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
        log.error("连接发生异常:{}", cause.getMessage());
    }
}
