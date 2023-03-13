package com.example.netty.im.domain;

import lombok.Data;

/**
 * @description: 客户端消息实体
 * @Date 2023/3/13 14:13
 * @Author fzy
 */
@Data
public class ClientMsgProtocol {

    /**
     * 1请求个人信息，2发送聊天信息
     */
    private int type;

    /**
     * 消息
     */
    private String msgInfo;

}
