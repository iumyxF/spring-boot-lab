package com.example.openvidu.entities.bo;

import lombok.Builder;
import lombok.Data;

/**
 * @author fzy
 * @description: 相关概念对象
 * <a href="https://docs.openvidu.io/en/stable/developing-your-video-app/" />
 * @date 2024/11/18 15:42
 */
@Data
@Builder
public class MediaUser {

    /**
     * 用户主键
     */
    private String userKey;

    /**
     * 用户类型 1：发布者， 2：订阅者
     */
    private Integer userType;

    /**
     * 会话ID
     */
    private String sessionId;

    /**
     * 用户连接会话ID
     */
    private String connId;

    /**
     * 会话访问凭证
     */
    private String token;
}
