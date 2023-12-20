package com.example.crawler.task;

import com.example.crawler.model.download.DownloadRequest;
import com.example.crawler.utils.DownloadUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author iumyxF
 * @description:
 * @date 2023/12/20 13:45
 */
@Slf4j
public class DownloadTask implements Runnable {

    private List<DownloadRequest> downloadRequestLit;

    public DownloadTask(List<DownloadRequest> downloadRequestLit) {
        this.downloadRequestLit = downloadRequestLit;
    }

    @Override
    public void run() {
        log.info("DownloadTask - " + Thread.currentThread().getName() + " 开始执行下载任务");
        downloadRequestLit.forEach(DownloadUtils::downloadFile);
    }
}
