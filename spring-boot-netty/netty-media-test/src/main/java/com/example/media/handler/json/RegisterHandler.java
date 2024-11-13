package com.example.media.handler.json;

import com.example.media.cache.MediaCacheHandler;
import com.example.media.common.bo.MediaBinaryData;
import com.example.media.common.bo.MediaJsonData;
import com.example.media.common.enums.JsonMessageType;
import io.netty.channel.Channel;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author feng
 * @description:
 * @date 2024/11/13 21:04
 */
@Component
public class RegisterHandler implements JsonMessageHandler {

    @Resource(name = "mediaThreadPoolTaskExecutor")
    private ThreadPoolTaskExecutor taskExecutor;
    @Resource
    private MediaCacheHandler mediaCacheHandler;

    @Override
    public int getHandlerType() {
        return JsonMessageType.REGISTER.getValue();
    }

    @Override
    public void handle(Channel channel, MediaJsonData data) {
        Map<String, Object> args = data.getArgs();
        if (null == args) {
            return;
        }
        String groupName = String.valueOf(args.get("group_name"));
        if (null == groupName) {
            return;
        }
        // 读取缓冲区数据并发送给当前channel
        taskExecutor.execute(() -> {
            List<MediaBinaryData> cacheData = mediaCacheHandler.get(groupName);
            cacheData.forEach(channel::writeAndFlush);
        });
    }
}
