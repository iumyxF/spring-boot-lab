package com.example.openvidu.service;

import com.example.openvidu.entities.bo.MediaUser;

/**
 * @author fzy
 * @description:
 * @date 2024/11/18 15:49
 */
public interface ScreenShareService {

    MediaUser createSession(String userKey);

    String join(String userKey, String sessionId);

    void quit(String userKey, String sessionId);

    void destroy(String sessionId);

    void checkSession(String sessionId);
}
