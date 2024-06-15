package com.example.vertx.client;

import com.example.vertx.common.codec.ProtocolConstant;
import com.example.vertx.common.codec.ProtocolMessageEncoder;
import com.example.vertx.common.codec.ProtocolMessageTypeEnum;
import com.example.vertx.common.model.ProtocolMessage;
import com.example.vertx.common.model.RpcConstant;
import com.example.vertx.common.model.RpcRequest;
import com.example.vertx.common.model.ServiceMetaInfo;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.impl.BufferImpl;
import io.vertx.core.net.NetSocket;

import java.io.IOException;

/**
 * @author iumyx
 * @description:
 * @date 2024/6/3 14:21
 */
public class TcpClient extends AbstractVerticle {

    @Override
    public void start(Promise<Void> startPromise) {
        vertx.createNetClient()
                .connect(18080, "localhost")
                .onComplete(socket -> {
                    NetSocket result = socket.result();
                    result.handler(buffer -> {
                        String content = buffer.toString("utf-8");
                        System.out.println("client received len: " + buffer.length() + " ,content: " + content);
                    });
                    result.closeHandler(handler -> System.out.println("socket close...."));
                })
                .onSuccess(handler -> {
                    BufferImpl bufferImpl = new BufferImpl();
                    RpcRequest rpcRequest = new RpcRequest();
                    rpcRequest.setServiceName("HelloService");
                    rpcRequest.setMethodName("sayHello");
                    rpcRequest.setServiceVersion(RpcConstant.DEFAULT_SERVICE_VERSION);
                    rpcRequest.setParameterTypes(new Class[]{String.class});
                    rpcRequest.setArgs(new Object[]{"jack"});

                    ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
                    serviceMetaInfo.setServiceName("HelloService");
                    serviceMetaInfo.setServiceVersion(RpcConstant.DEFAULT_SERVICE_VERSION);
                    serviceMetaInfo.setServiceHost("192.168.2.199");
                    serviceMetaInfo.setServicePort(18080);
                    serviceMetaInfo.setServiceGroup(RpcConstant.DEFAULT_GROUP);
                    ProtocolMessage<RpcRequest> protocolRequest = buildProtocolMessage(rpcRequest);

                    try {
                        byte[] bytes = ProtocolMessageEncoder.encode(protocolRequest);
                        bufferImpl.appendBytes(bytes);
                        handler.write(bufferImpl);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    public static void main(String[] args) {
        Vertx vertx2 = Vertx.vertx();
        vertx2.deployVerticle(new TcpClient());
    }

    private static ProtocolMessage<RpcRequest> buildProtocolMessage(RpcRequest rpcRequest) {
        ProtocolMessage<RpcRequest> protocolMessage = new ProtocolMessage<>();
        ProtocolMessage.Header header = new ProtocolMessage.Header();
        header.setMagic(ProtocolConstant.PROTOCOL_MAGIC);
        header.setVersion(ProtocolConstant.PROTOCOL_VERSION);
        header.setSerializer((byte) 1);
        header.setType((byte) ProtocolMessageTypeEnum.REQUEST.getKey());
        header.setRequestId(123L);
        protocolMessage.setHeader(header);
        protocolMessage.setBody(rpcRequest);
        return protocolMessage;
    }
}
