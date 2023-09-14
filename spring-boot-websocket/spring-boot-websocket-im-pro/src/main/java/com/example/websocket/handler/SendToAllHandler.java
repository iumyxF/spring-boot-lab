package com.example.websocket.handler;

import com.example.websocket.entities.SendResponse;
import com.example.websocket.entities.SendToAllRequest;
import com.example.websocket.entities.SendToUserRequest;
import com.example.websocket.utils.WebSocketUtil;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;


/**
 * @description: 广播消息处理器
 * @Date 2023/2/27 15:09
 * @author iumyxF
 */
@Component
public class SendToAllHandler implements MessageHandler<SendToAllRequest> {
    @Override
    public void execute(WebSocketSession session, SendToAllRequest message) {
        // 这里，假装直接成功
        SendResponse sendResponse = SendResponse.builder()
                .msgId(message.getMsgId())
                .code(HttpStatus.OK.value())
                .build();
        WebSocketUtil.send(session, SendResponse.TYPE, sendResponse);

        // 创建转发的消息
        SendToUserRequest sendToUserRequest = SendToUserRequest.builder()
                .msgId(message.getMsgId())
                .content(message.getContent())
                .build();
        // 广播发送
        WebSocketUtil.broadcast(SendToUserRequest.TYPE, sendToUserRequest);
    }

    @Override
    public String getType() {
        return SendToAllRequest.TYPE;
    }
}
