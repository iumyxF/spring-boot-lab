package com.example.netty.file.util;

import com.example.netty.file.domain.FileBurstInstruct;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: 文件缓存
 * @Date 2023/3/11 11:00
 * @Author fzy
 */
public class CacheUtil {

    public static Map<String, FileBurstInstruct> burstDataMap = new ConcurrentHashMap<>();

}
