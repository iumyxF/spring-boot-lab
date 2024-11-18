package com.example.media.cache;

import com.example.media.common.bo.MediaBinaryData;

import java.util.List;

/**
 * @author feng
 * @description:
 * @date 2024/11/17 22:46
 */
public interface CacheHandler {

    void put(String groupName, MediaBinaryData data);

    List<MediaBinaryData> get(String groupName);

    void clear(String groupName);
}
