package com.example.websocket.handler;

import com.example.websocket.entities.Message;
import org.springframework.web.socket.WebSocketSession;

/**
 * @description: 消息处理器
 * @Date 2023/2/27 14:40
 * @Author fzy
 */
public interface MessageHandler<T extends Message> {

    /**
     * 执行处理消息
     *
     * @param session 会话
     * @param message 消息
     */
    void execute(WebSocketSession session, T message);

    /**
     * 获取消息类型，即每个 Message 实现类上的 TYPE 静态字段
     *
     * @return 消息类型
     */
    String getType();
}
