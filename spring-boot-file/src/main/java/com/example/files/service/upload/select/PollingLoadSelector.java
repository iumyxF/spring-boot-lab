package com.example.files.service.upload.select;

import com.example.files.service.upload.uploader.Uploader;
import com.example.files.service.upload.uploader.UploaderRepository;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author iumyx
 * @description: 轮询选择
 * @date 2024/6/27 10:14
 */
public class PollingLoadSelector implements Selector {

    /**
     * 当前轮询的下标
     */
    private final AtomicInteger currentIndex = new AtomicInteger(0);

    @Override
    public Uploader select() {
        List<Uploader> list = UploaderRepository.list();
        if (null == list || list.isEmpty()) {
            return null;
        }
        if (1 == list.size()) {
            return list.get(0);
        }
        int index = currentIndex.getAndUpdate(current -> (current == Integer.MAX_VALUE) ? 0 : current + 1) % list.size();
        return list.get(index);
    }
}
