package com.example.files.service.upload.select;

import com.example.files.service.upload.uploader.Uploader;
import com.example.files.service.upload.uploader.UploaderRepository;

import java.util.List;
import java.util.Random;

/**
 * @author iumyx
 * @description:
 * @date 2024/6/27 10:30
 */
public class RandomSelector implements Selector {

    private final Random random = new Random();

    @Override
    public Uploader select() {
        List<Uploader> list = UploaderRepository.list();
        if (null == list || list.isEmpty()) {
            return null;
        }
        if (1 == list.size()) {
            return list.get(0);
        }
        return list.get(random.nextInt(list.size()));
    }
}