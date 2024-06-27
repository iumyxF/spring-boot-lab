package com.example.files.service.upload.uploader;

import com.example.files.model.dto.UploadCredential;
import com.example.files.model.dto.UploadResponse;

/**
 * @author iumyx
 * @description:
 * @date 2024/6/27 14:21
 */
public class TencentUploader extends AbstractUploader{
    @Override
    protected UploadCredential getCredential() {
        // 略
        return null;
    }

    @Override
    protected UploadResponse upload(UploadCredential credential) {
        // 略
        return null;
    }
}
