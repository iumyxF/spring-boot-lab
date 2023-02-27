package com.example.websocket.server;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.example.websocket.entities.AuthRequest;
import com.example.websocket.entities.Message;
import com.example.websocket.handler.MessageHandler;
import com.example.websocket.utils.WebSocketUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @description: websocket服务端点
 * 测试使用：
 * ws://127.0.0.1:8080/websocket/im?token=
 * {"type":"SEND_TO_ONE_REQUEST","body":{"toUser":"番茄","msgId":"eaef4a3c-35dd-46ee-b548-f9c4eb6396fe","content":"我是一条单聊消息"}}
 * {"type":"SEND_TO_ALL_REQUEST","body":{"msgId":"838e97e1-6ae9-40f9-99c3-f7127ed64747","content":"我是一条群聊消息"}}
 * @Date 2023/2/27 14:14
 * @Author fzy
 */
@Component
@ServerEndpoint("/websocket/im")
public class WebsocketServerEndpoint implements InitializingBean {

    @Resource
    private ApplicationContext applicationContext;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 消息类型与 MessageHandler 的映射
     * 注意，这里设置成静态变量。虽然说 WebsocketServerEndpoint 是单例，但是 Spring Boot 还是会为每个 WebSocket 创建一个 WebsocketServerEndpoint Bean 。
     */
    private static final Map<String, MessageHandler> HANDLERS = new HashMap<>(5);

    @Override
    public void afterPropertiesSet() {
        //把所有消息注册器注入到Map中
        applicationContext.getBeansOfType(MessageHandler.class).values()
                .forEach(messageHandler -> HANDLERS.put(messageHandler.getType(), messageHandler));
        logger.info("[afterPropertiesSet][消息处理器数量：{}]", HANDLERS.size());
    }

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        logger.info("[onOpen][session({}) 接入]", session);
        //获取token创建请求认证消息
        List<String> tokens = session.getRequestParameterMap().get("token");
        String token = CollectionUtils.isEmpty(tokens) ? null : tokens.get(0);
        AuthRequest request = AuthRequest.builder().token(token).build();
        //获取消息处理器
        MessageHandler<AuthRequest> handler = HANDLERS.get(AuthRequest.TYPE);
        if (Objects.isNull(handler)) {
            logger.error("[onOpen][认证消息类型，不存在消息处理器]");
            return;
        }
        handler.execute(session, request);
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        // 生产环境下，请设置成 debug 级别
        logger.info("[onOpen][session({}) 接收到一条消息({})]", session, message);
        // 获取消息类型
        try {
            JSONObject jsonMessage = JSON.parseObject(message);
            String messageType = jsonMessage.getString("type");
            MessageHandler handler = HANDLERS.get(messageType);
            if (Objects.isNull(handler)) {
                logger.error("[onOpen][认证消息类型，不存在消息处理器]");
                return;
            }
            //解析消息
            Class<? extends Message> messageClass = this.getMessageClass(handler);
            //处理消息
            Message messageObj = JSON.parseObject(jsonMessage.getString("body"), messageClass);
            handler.execute(session, messageObj);
        } catch (Throwable throwable) {
            logger.info("[onMessage][session({}) message({}) 发生异常]", session, throwable);
        }
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        logger.info("[onClose][session({}) 连接关闭。关闭原因是({})}]", session, closeReason);
        //移除用户session
        WebSocketUtil.removeSession(session);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        logger.info("[onClose][session({}) 发生异常]", session, throwable);
    }

    /**
     * 通过 MessageHandler 中，通过解析其类上的泛型，获得消息类型对应的 Class 类
     *
     * @return 消息类型
     */
    private Class<? extends Message> getMessageClass(MessageHandler handler) {
        // 获得 Bean 对应的 Class 类名。因为有可能被 AOP 代理过。
        Class<?> targetClass = AopProxyUtils.ultimateTargetClass(handler);
        // 获得接口的 Type 数组
        Type[] interfaces = targetClass.getGenericInterfaces();
        Class<?> superclass = targetClass.getSuperclass();
        //此处，是以父类的接口为准
        while ((Objects.isNull(interfaces) || 0 == interfaces.length) && Objects.nonNull(superclass)) {
            interfaces = superclass.getGenericInterfaces();
            superclass = targetClass.getSuperclass();
        }
        if (Objects.nonNull(interfaces)) {
            // 遍历 interfaces 数组
            for (Type type : interfaces) {
                // 要求 type 是泛型参数
                if (type instanceof ParameterizedType) {
                    ParameterizedType parameterizedType = (ParameterizedType) type;
                    // 要求是 MessageHandler 接口
                    if (Objects.equals(parameterizedType.getRawType(), MessageHandler.class)) {
                        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                        // 取首个元素
                        if (Objects.nonNull(actualTypeArguments) && actualTypeArguments.length > 0) {
                            return (Class<Message>) actualTypeArguments[0];
                        } else {
                            throw new IllegalStateException(String.format("类型(%s) 获得不到消息类型", handler));
                        }
                    }
                }
            }
        }
        throw new IllegalStateException(String.format("类型(%s) 获得不到消息类型", handler));
    }

}
