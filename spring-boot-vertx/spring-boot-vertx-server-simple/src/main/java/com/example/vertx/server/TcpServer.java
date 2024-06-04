package com.example.vertx.server;

import com.example.vertx.common.model.RpcResponse;
import com.example.vertx.common.serializer.JsonSerializer;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;

import java.io.IOException;

/**
 * @author fzy
 * @description:
 * @date 2024/6/3 14:16
 */
public class TcpServer extends AbstractVerticle {

    @Override
    public void start(Promise<Void> startPromise) {
        vertx.createNetServer()
                .connectHandler(new TcpServerHandler())
                .exceptionHandler(Throwable::printStackTrace)
                .listen(18080)
                .onSuccess(handler -> System.out.println("tcp server start up in port 18080 success!"));
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, IOException {
        Vertx vertx2 = Vertx.vertx();
        vertx2.deployVerticle(new TcpServer());

        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setData(null);
        rpcResponse.setDataType(null);
        rpcResponse.setMessage(null);
        rpcResponse.setException(NullPointerException.class.newInstance());

        JsonSerializer serializer = new JsonSerializer();
        byte[] bytes = serializer.serialize(rpcResponse);
        System.out.println(bytes.length);

        RpcResponse response = serializer.deserialize(bytes, RpcResponse.class);
        System.out.println(response);
    }
}
