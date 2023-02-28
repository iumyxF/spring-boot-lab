package com.example.websocket.entities;

/**
 * @description: 基础消息体，所有消息体都要实现该接口
 * 消息样式：
 * {
 * type: "", // 消息类型
 * body: {} // 消息体
 * }
 * 消息可以根据不同业务进行扩展：用户认证请求、删除一个好友请求等。
 * @Date 2023/2/27 14:21
 * @Author fzy
 */
public interface Message {
}
