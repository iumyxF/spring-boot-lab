package com.example.media.cache;

import com.example.media.common.bo.MediaBinaryData;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author feng
 * @description: 软编码和硬编码的关键帧判断方式不相同
 * @date 2024/11/13 21:58
 */
@Component
public class MediaCacheHandler {

    /*
     cacheHandler 读读共享，读写互斥，同一个组内只有一个能写，用ReentrantReadWriteLock
     */

    public void put(String groupName, MediaBinaryData data) {

    }

    public List<MediaBinaryData> get(String groupName) {
        return null;
    }

    public void clear(String groupName) {

    }
}
