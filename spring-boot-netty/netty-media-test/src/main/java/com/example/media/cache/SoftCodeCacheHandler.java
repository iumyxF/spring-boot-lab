package com.example.media.cache;

import com.example.media.common.bo.MediaBinaryData;

import java.util.List;

/**
 * @author feng
 * @description:
 * @date 2024/11/17 22:46
 */
public class SoftCodeCacheHandler implements CacheHandler{
    @Override
    public void put(String groupName, MediaBinaryData data) {

    }

    @Override
    public List<MediaBinaryData> get(String groupName) {
        return null;
    }

    @Override
    public void clear(String groupName) {

    }
}
