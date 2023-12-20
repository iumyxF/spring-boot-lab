package com.example.crawler.model.download;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author iumyxF
 * @description:
 * @date 2023/12/19 10:17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DownloadRequest {

    private String downloadUrl;

    private String fileName;

    private String filePath;
}
