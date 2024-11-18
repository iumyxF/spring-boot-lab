package com.example.openvidu.service.impl;

import com.example.openvidu.config.OpenviduProperties;
import com.example.openvidu.entities.bo.MediaUser;
import com.example.openvidu.entities.po.User;
import com.example.openvidu.service.ScreenShareService;
import com.example.openvidu.service.UserService;
import io.openvidu.java.client.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author fzy
 * @description: openvidu 维护了 sessionId和 session的Map
 * session 维护了 connectionId和connection的Map
 * @date 2024/11/18 15:51
 */
@Service
public class ScreenShareServiceImpl implements ScreenShareService {

    @Resource
    private OpenviduProperties openviduProperties;
    @Resource
    private UserService userService;

    OpenVidu openvidu = new OpenVidu(openviduProperties.getUrl(), openviduProperties.getSecret());

    /**
     * key = userKey
     * value = connectionId
     */
    HashMap<String, String> userKeyConnIdMap = new HashMap<>();

    @Override
    public MediaUser createSession(String userKey) {
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
                    .token(connection.getToken())
                    .build();
        } catch (OpenViduJavaClientException | OpenViduHttpException e) {
            throw new RuntimeException(e);
        }
    }

    public String join(String userKey, String sessionId) {
        User user = userService.getByKey(userKey);
        if (null == user) {
            throw new RuntimeException("user not found");
        }
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
            return connection.getToken();
        } catch (OpenViduJavaClientException | OpenViduHttpException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void quit(String userKey, String sessionId) {
    }

    @Override
    public void destroy(String sessionId) {

    }

    @Override
    public void checkSession(String sessionId) {

    }
}
