package com.example.vertx.server;

import com.example.vertx.common.codec.ProtocolMessageDecoder;
import com.example.vertx.common.model.ProtocolMessage;
import com.example.vertx.common.model.RpcRequest;
import io.vertx.core.Handler;
import io.vertx.core.net.NetSocket;

import java.io.IOException;

/**
 * @author iumyx
 * @description:
 * @date 2024/6/3 15:30
 */
public class TcpServerHandler implements Handler<NetSocket> {
    @Override
    public void handle(NetSocket socket) {
        TcpBufferHandlerWrapper handlerWrapper = new TcpBufferHandlerWrapper(buffer -> {
            System.out.println(buffer.length());
            // 接受请求，解码
            ProtocolMessage<RpcRequest> protocolMessage;
            try {
                protocolMessage = (ProtocolMessage<RpcRequest>) ProtocolMessageDecoder.decode(buffer.getBytes());
            } catch (IOException e) {
                throw new RuntimeException("协议消息解码错误");
            }
            System.out.println(protocolMessage);
        });
        socket.handler(handlerWrapper);
    }
}
