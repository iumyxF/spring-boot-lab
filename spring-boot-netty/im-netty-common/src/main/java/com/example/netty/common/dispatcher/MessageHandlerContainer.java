package com.example.netty.common.dispatcher;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @description:
 * @Date 2023/3/14 11:06
 * @author iumyxF
 */
@Slf4j
public class MessageHandlerContainer implements InitializingBean {

    /**
     * 消息类型与 MessageHandler 的映射
     */
    private final Map<String, MessageHandler> handlers = new HashMap<>();

    @Resource
    private ApplicationContext applicationContext;

    /**
     * 初始化获取所有messageHandler
     */
    @Override
    public void afterPropertiesSet() {
        // 获得所有 MessageHandler Bean
        applicationContext.getBeansOfType(MessageHandler.class).values()
                // 添加到 handlers 中
                .forEach(messageHandler -> handlers.put(messageHandler.getType(), messageHandler));
        log.info("[afterPropertiesSet][消息处理器数量：{}]", handlers.size());
    }

    /**
     * 根据类型获取对应的MessageHandler
     *
     * @param type 类型
     * @return MessageHandler
     */
    public MessageHandler getMessageHandler(String type) {
        MessageHandler handler = handlers.get(type);
        if (handler == null) {
            throw new IllegalArgumentException(String.format("类型(%s) 找不到匹配的 MessageHandler 处理器", type));
        }
        return handler;
    }

    /**
     * 获得 MessageHandler 处理的消息类
     *
     * @param handler 处理器
     * @return 消息类
     */
    public static Class<? extends Message> getMessageClass(MessageHandler handler) {
        // 获得 Bean 对应的 Class 类名。因为有可能被 AOP 代理过。
        Class<?> targetClass = AopProxyUtils.ultimateTargetClass(handler);
        // 获得接口的 Type 数组
        Type[] interfaces = targetClass.getGenericInterfaces();
        Class<?> superclass = targetClass.getSuperclass();
        // 此处，是以父类的接口为准
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
