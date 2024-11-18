package com.example.media.handler.binary;

import com.example.media.common.bo.MediaBinaryData;
import com.example.media.common.enums.BinaryMessageType;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
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
 * @date 2024/11/13 20:57
 */
@Slf4j
@ChannelHandler.Sharable
@Component
public class TcpBinaryHandler implements InitializingBean {

    /**
     * 消息类型与 MessageHandler 的映射
     */
    private final Map<Integer, BinaryMessageHandler> handlers = new HashMap<>();

    @Resource
    private ApplicationContext applicationContext;

    @Override
    public void afterPropertiesSet() {
        applicationContext.getBeansOfType(BinaryMessageHandler.class).values()
                .forEach(messageHandler -> handlers.put(messageHandler.getHandlerType(), messageHandler));
    }

    /**
     * 处理 MediaBinaryData
     *
     * @param channel channel
     * @param data    MediaBinaryData
     */
    public void handle(Channel channel, MediaBinaryData data) {
        BinaryMessageHandler handler = getMessageHandler(data);
        if (null == handler) {
            return;
        }
        handler.handle(channel, data);
    }

    /**
     * 根据 MediaBinaryData 类型获取 对应 handler
     *
     * @param data MediaBinaryData
     * @return handler
     */
    private BinaryMessageHandler getMessageHandler(MediaBinaryData data) {
        if (null == data) {
            return null;
        }
        Integer type = getMessageType(data.getType());
        if (null == type) {
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
    private BinaryMessageHandler getMessageHandler(Integer type) {
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
    private Integer getMessageType(byte type) {
        int value = (type >> 2) & 0x0F;
        BinaryMessageType messageType = BinaryMessageType.getTypeByValue(value);
        if (null == messageType) {
            return null;
        }
        return messageType.getValue();
    }
}
