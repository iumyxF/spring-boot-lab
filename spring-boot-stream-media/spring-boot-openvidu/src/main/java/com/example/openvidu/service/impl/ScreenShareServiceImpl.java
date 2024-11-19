package com.example.openvidu.service.impl;

import com.example.openvidu.entities.bo.MediaUser;
import com.example.openvidu.entities.po.User;
import com.example.openvidu.service.ScreenShareService;
import com.example.openvidu.service.UserService;
import io.openvidu.java.client.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author fzy
 * @description: openvidu 维护了 sessionId和 session的 Map
 * session 维护了 connectionId和connection的Map
 * @date 2024/11/18 15:51
 */
@Service
public class ScreenShareServiceImpl implements ScreenShareService {

    @Resource
    private UserService userService;
    @Resource
    private OpenVidu openvidu;

    /**
     * key = userKey
     * value = connectionId
     */
    private final Map<String, String> userKeyConnIdMap = new ConcurrentHashMap<>();

    @Override
    public MediaUser createSession(String userKey) {
        if (!StringUtils.hasText(userKey)) {
            throw new RuntimeException("userKey param error");
        }
        synchronized (userKey.intern()) {
            try {
                // 创建会话
                SessionProperties properties = new SessionProperties.Builder()
                        .forcedVideoCodec(VideoCodec.H264)
                        .forcedVideoCodecResolved(VideoCodec.H264)
                        .build();
                Session session = openvidu.createSession(properties);

                // 将创建者作为发布者加入到session中
                ConnectionProperties connectionProperties = new ConnectionProperties.Builder()
                        .type(ConnectionType.WEBRTC)
                        .role(OpenViduRole.PUBLISHER)
                        .data(userKey)
                        .build();
                Connection connection = session.createConnection(connectionProperties);
                userKeyConnIdMap.put(userKey, session.getSessionId());

                // 返回用户的连接信息
                return MediaUser.builder()
                        .userKey(userKey)
                        .userType(1)
                        .sessionId(session.getSessionId())
                        .connId(connection.getConnectionId())
                        .token(connection.getToken())
                        .build();
            } catch (OpenViduJavaClientException | OpenViduHttpException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public MediaUser join(String userKey, String sessionId) {
        if (!StringUtils.hasText(userKey) || !StringUtils.hasText(sessionId)) {
            throw new RuntimeException("param error");
        }
        User user = checkUserByKey(userKey);
        synchronized (userKey.intern()) {
            try {
                Session session = openvidu.getActiveSession(sessionId);
                ConnectionProperties connectionProperties = new ConnectionProperties.Builder()
                        .type(ConnectionType.WEBRTC)
                        // 参与的用户只能作为订阅者
                        .role(OpenViduRole.SUBSCRIBER)
                        .data(userKey)
                        .build();
                Connection connection = session.createConnection(connectionProperties);
                userKeyConnIdMap.put(userKey, connection.getConnectionId());
                return MediaUser.builder()
                        .userKey(user.getUserKey())
                        .userType(2)
                        .sessionId(sessionId)
                        .connId(connection.getConnectionId())
                        .token(connection.getToken())
                        .build();
            } catch (OpenViduJavaClientException | OpenViduHttpException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void quit(String userKey, String sessionId) {
        if (!StringUtils.hasText(userKey) || !StringUtils.hasText(sessionId)) {
            throw new RuntimeException("param error");
        }
        synchronized (userKey.intern()) {
            Session session = openvidu.getActiveSession(sessionId);
            if (null == session) {
                throw new RuntimeException("session not found");
            }
            String connId = userKeyConnIdMap.get(userKey);
            if (null == connId) {
                throw new RuntimeException("user connection id not found");
            }
            try {
                session.forceDisconnect(connId);
            } catch (OpenViduJavaClientException | OpenViduHttpException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void destroy(String sessionId) {
        synchronized (sessionId.intern()) {
            Session session = openvidu.getActiveSession(sessionId);
            if (null == session) {
                throw new RuntimeException("session not found");
            }
            try {
                session.close();
            } catch (OpenViduJavaClientException | OpenViduHttpException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private User checkUserByKey(String userKey) {
        User user = userService.getByKey(userKey);
        if (null == user) {
            throw new RuntimeException("user not found");
        }
        return user;
    }
}