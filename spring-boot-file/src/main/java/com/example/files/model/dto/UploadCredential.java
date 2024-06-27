package com.example.files.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author iumyx
 * @description: 凭证信息
 * @date 2024/6/27 14:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UploadCredential {

    /**
     * ak
     */
    private String accessKey;

    /**
     * sk
     */
    private String secretAccessKey;

    /**
     * bucket
     */
    private String bucket;

    /**
     * 区域
     */
    private String region;
}
