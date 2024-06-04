package com.example.vertx.common.codec;

import com.example.vertx.common.model.ProtocolMessage;
import com.example.vertx.common.model.RpcConstant;
import com.example.vertx.common.model.RpcRequest;
import com.example.vertx.common.model.ServiceMetaInfo;

import java.io.IOException;
import java.util.Arrays;

/**
 * @author fzy
 * @description:
 * @date 2024/6/3 15:59
 */
public class ProtocolTest {

    public static void main(String[] args) throws IOException {
        ProtocolMessage<RpcRequest> request = buildRequest();
        byte[] encode = ProtocolMessageEncoder.encode(request);
        System.out.println(encode.length);
        System.out.println(Arrays.toString(encode));

        ProtocolMessage<RpcRequest> requestProtocolMessage =
                (ProtocolMessage<RpcRequest>) ProtocolMessageDecoder.decode(encode);
        System.out.println(requestProtocolMessage);
    }

    public static ProtocolMessage<RpcRequest> buildRequest() {
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

        ProtocolMessage<RpcRequest> protocolMessage = new ProtocolMessage<>();
        ProtocolMessage.Header header = new ProtocolMessage.Header();
        header.setMagic(ProtocolConstant.PROTOCOL_MAGIC);
        header.setVersion(ProtocolConstant.PROTOCOL_VERSION);
        header.setSerializer((byte) 1);
        header.setType((byte) ProtocolMessageTypeEnum.REQUEST.getKey());
        header.setStatus((byte) 0);
        header.setRequestId(123L);
        protocolMessage.setHeader(header);
        protocolMessage.setBody(rpcRequest);
        return protocolMessage;
    }
}