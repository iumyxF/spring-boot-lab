package com.example.netty.im.domain;

import lombok.Data;

/**
 * @description: 服务端消息实体
 * @Date 2023/3/13 14:17
 * @author iumyxF
 */
@Data
public class ServerMsgProtocol {

    /**
     * 链接信息;1自发信息、2群发消息
     */
    private int type;

    /**
     * 通信管道ID，实际使用中会映射成用户名
     */
    private String channelId;

    /**
     * 用户头像[模拟分配]
     */
    private String userHeadImg;

    /**
     * 通信消息
     */
    private String msgInfo;

}
