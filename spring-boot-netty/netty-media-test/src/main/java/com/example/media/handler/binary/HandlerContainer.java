package com.example.media.handler.binary;

import com.example.media.common.bo.MediaBinaryData;
import com.example.media.common.enums.BinaryMessageType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author feng
 * @description:
 * @date 2024/11/13 20:58
 */
@Slf4j
@Component
public class HandlerContainer implements InitializingBean {

    /**
     * 消息类型与 MessageHandler 的映射
     */
    private final Map<Integer, BinaryMessageHandler> handlers = new HashMap<>();

    @Resource
    private ApplicationContext applicationContext;

    @Override
    public void afterPropertiesSet() {
        // 获得所有 MessageHandler Bean
        applicationContext.getBeansOfType(BinaryMessageHandler.class).values()
                // 添加到 handlers 中
                .forEach(messageHandler -> handlers.put(messageHandler.getHandlerType(), messageHandler));
    }

    public BinaryMessageHandler getMessageHandler(MediaBinaryData data) {
        if (null == data){
            return null;
        }
        Integer type = getMessageType(data.getType());
        if (null == type){
            return null;
        }
        return getMessageHandler(type);
    }

    /**
     * 根据类型获取对应的MessageHandler
     *
     * @param type 类型
     * @return MessageHandler 消息处理器
     */
    public BinaryMessageHandler getMessageHandler(Integer type) {
        BinaryMessageHandler handler = handlers.get(type);
        if (null == handler) {
            throw new IllegalArgumentException(String.format("类型(%s) 找不到匹配的 MessageHandler 处理器", type));
        }
        return handler;
    }

    /**
     * 根据 MediaBinaryData 的 type 字段，获取所属类型
     *
     * @param type MediaBinaryData.getType()
     * @return {@link BinaryMessageType}
     */
    public Integer getMessageType(byte type) {
        int value = (type >> 2) & 0x0F;
        BinaryMessageType messageType = BinaryMessageType.getTypeByValue(value);
        if (null == messageType) {
            return null;
        }
        return messageType.getValue();
    }
}
