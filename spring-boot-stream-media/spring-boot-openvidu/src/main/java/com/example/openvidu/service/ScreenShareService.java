package com.example.openvidu.service;

import com.example.openvidu.entities.bo.MediaUser;

/**
 * @author fzy
 * @description:
 * @date 2024/11/18 15:49
 */
public interface ScreenShareService {

    /**
     * 创建session(创建一个会议室)
     *
     * @param userKey 发起通讯的用户主键
     * @return MediaUser
     */
    MediaUser createSession(String userKey);

    /**
     * 加入到某个session
     *
     * @param userKey   用户主键
     * @param sessionId sessionId
     * @return MediaUser
     */
    MediaUser join(String userKey, String sessionId);

    /**
     * 退出某个session
     *
     * @param userKey   用户主键
     * @param sessionId sessionId
     */
    void quit(String userKey, String sessionId);

    /**
     * 结束某个session
     *
     * @param sessionId sessionId
     */
    void destroy(String sessionId);
}
