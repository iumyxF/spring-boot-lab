package com.example.websocket.handler;

import com.example.websocket.entities.SendResponse;
import com.example.websocket.entities.SendToOneRequest;
import com.example.websocket.entities.SendToUserRequest;
import com.example.websocket.utils.WebSocketUtil;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.websocket.Session;

/**
 * @description: 发送给一个人的消息处理器
 * @Date 2023/2/27 15:05
 * @author iumyxF
 */
@Component
public class SendToOneHandler implements MessageHandler<SendToOneRequest> {

    @Override
    public void execute(Session session, SendToOneRequest message) {
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
        WebSocketUtil.send(message.getToUser(), SendToUserRequest.TYPE, sendToUserRequest);
    }

    @Override
    public String getType() {
        return SendToOneRequest.TYPE;
    }
}
