package com.example.files.service.upload;

import com.example.files.service.upload.retry.RetryStrategy;
import com.example.files.service.upload.retry.RetryStrategyFactory;
import com.example.files.service.upload.select.Selector;
import com.example.files.service.upload.select.SelectorFactory;
import com.example.files.service.upload.uploader.Uploader;

import java.io.File;

/**
 * @author iumyx
 * @description:
 * @date 2024/6/27 10:11
 */
public class PicUploadServiceImpl implements PicUploadService {

    /**
     * 文件上传具体实现
     * 1. 通过选择器选出服务商
     * 2. 调用doUpload方法
     *
     * @param file pic file
     * @return 文件访问地址
     */
    @Override
    public String upload(File file) {
        Selector selector = SelectorFactory.getDefaultInstance();
        Uploader uploader = selector.select();
        RetryStrategy retryStrategy = RetryStrategyFactory.getDefaultInstance();
        try {
            return retryStrategy.doRetry(() -> uploader.doUpload(file));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
