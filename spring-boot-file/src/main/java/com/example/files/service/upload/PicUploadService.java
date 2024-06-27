package com.example.files.service.upload;

import java.io.File;

/**
 * @author iumyx
 * @description:
 * @date 2024/6/27 10:10
 */
public interface PicUploadService {

    /**
     * pic upload
     *
     * @param file pic file
     * @return pic link address
     */
    String upload(File file);
}
