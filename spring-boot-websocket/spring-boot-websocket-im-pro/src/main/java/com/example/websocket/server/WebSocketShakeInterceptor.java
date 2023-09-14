package com.example.websocket.server;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import java.util.Map;

/**
 * @description: 创建WebSession拦截器
 * @Date 2023/2/28 10:04
 * @author iumyxF
 */
public class WebSocketShakeInterceptor extends HttpSessionHandshakeInterceptor {

    /**
     * 握手前执行
     */
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        // 获得 token
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest serverRequest = (ServletServerHttpRequest) request;
            attributes.put("token", serverRequest.getServletRequest().getParameter("token"));
        }
        // 调用父方法，继续执行逻辑
        return super.beforeHandshake(request, response, wsHandler, attributes);
    }
}
