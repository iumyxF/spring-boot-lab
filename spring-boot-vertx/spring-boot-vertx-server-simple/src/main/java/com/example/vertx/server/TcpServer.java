package com.example.vertx.server;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;

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

    public static void main(String[] args) {
        Vertx vertx2 = Vertx.vertx();
        vertx2.deployVerticle(new TcpServer());
    }
}
