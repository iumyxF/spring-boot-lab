package com.example.websocket.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.websocket.entities.AuthRequest;
import com.example.websocket.entities.Message;
import com.example.websocket.handler.MessageHandler;
import com.example.websocket.utils.WebSocketUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @description: WebSocket处理器
 * @Date 2023/2/28 10:07
 * @Author fzy
 */
public class WebSocketHandler extends TextWebSocketHandler implements InitializingBean {

    @Resource
    private ApplicationContext applicationContext;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private static final Map<String, MessageHandler> HANDLERS = new HashMap<>(5);

    @Override
    public void afterPropertiesSet() {
        //把所有消息注册器注入到Map中
        applicationContext.getBeansOfType(MessageHandler.class).values()
                .forEach(messageHandler -> HANDLERS.put(messageHandler.getType(), messageHandler));
        logger.info("[afterPropertiesSet][消息处理器数量：{}]", HANDLERS.size());
    }

    /**
     * 建立连接后处理器 onOpen
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        logger.info("[afterConnectionEstablished][session({}) 接入]", session);
        // 解析 token
        String token = (String) session.getAttributes().get("token");
        // 创建 AuthRequest 消息类型
        AuthRequest authRequest = AuthRequest.builder().token(token).build();
        // 获得消息处理器
        MessageHandler<AuthRequest> messageHandler = HANDLERS.get(AuthRequest.TYPE);
        if (messageHandler == null) {
            logger.error("[afterConnectionEstablished][认证消息类型，不存在消息处理器]");
            return;
        }
        messageHandler.execute(session, authRequest);
    }

    /**
     * Message事件处理器
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        // 生产环境下，请设置成 debug 级别
        logger.info("[handleTextMessage][session({}) 接收到一条消息({})]", session, message);
        try {
            // 获得消息类型
            JSONObject jsonMessage = JSON.parseObject(message.getPayload());
            String messageType = jsonMessage.getString("type");
            // 获得消息处理器
            MessageHandler messageHandler = HANDLERS.get(messageType);
            if (messageHandler == null) {
                logger.error("[handleTextMessage][消息类型({}) 不存在消息处理器]", messageType);
                return;
            }
            // 解析消息
            Class<? extends Message> messageClass = this.getMessageClass(messageHandler);
            // 处理消息
            Message messageObj = JSON.parseObject(jsonMessage.getString("body"), messageClass);
            messageHandler.execute(session, messageObj);
        } catch (Throwable throwable) {
            logger.info("[handleTextMessage][session({}) message({}) 发生异常]", session, throwable);
        }
    }

    /**
     * 异常处理 onError
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) {
        logger.info("[handleTransportError][session({}) 发生异常]", session, exception);
    }

    /**
     * 连接关闭处理器 onClose
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        logger.info("[afterConnectionClosed][session({}) 连接关闭。关闭原因是({})}]", session, status);
        WebSocketUtil.removeSession(session);
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
