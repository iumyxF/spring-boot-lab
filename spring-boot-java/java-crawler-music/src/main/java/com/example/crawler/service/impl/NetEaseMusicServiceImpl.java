package com.example.crawler.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpStatus;
import com.alibaba.fastjson2.JSONObject;
import com.example.crawler.model.download.DownloadRequest;
import com.example.crawler.model.search.Artist;
import com.example.crawler.model.search.Music;
import com.example.crawler.service.IMusicService;
import com.example.crawler.utils.DownloadUtils;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author iumyxF
 * @description: 网易云
 * @date 2023/12/19 9:58
 */
@Slf4j
@Service
public class NetEaseMusicServiceImpl implements IMusicService {

    @Value("${crawler.path}")
    private String path;

    @Resource(name = "threadPoolTaskExecutor")
    private ThreadPoolTaskExecutor taskExecutor;

    /**
     * https://music.163.com/api/search/get/web?type=1&offset=2&total=false&limit=2&s=%E5%91%A8%E6%9D%B0%E4%BC%A6
     */
    private static final String SEARCH_MUSIC_URL = "https://music.163.com/api/search/get/web";

    private static final String DOWNLOAD_URL = "http://music.163.com/song/media/outer/url?id=%s.mp3";

    @PostConstruct
    public void init() {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    @Override
    public List<Music> searchMusic(Integer page, Integer size, String key) {
        HashMap<String, Object> params = new HashMap<>(5);
        params.put("type", "1");
        params.put("offset", Math.max(0, page - 1) * size);
        params.put("total", "false");
        params.put("limit", size);
        params.put("s", key);

        String resultJson = HttpRequest.get(SEARCH_MUSIC_URL)
                .form(params)
                .execute()
                .body();
        JSONObject result = JSONObject.parse(resultJson);
        if (HttpStatus.HTTP_OK == result.getInteger("code")) {
            List<Music> musicList = result.getJSONObject("result")
                    .getJSONArray("songs")
                    .toList(Music.class);
            log.info("musicList Size = {}", musicList.size());
            return musicList;
        }
        return null;
    }

    /**
     * 网易下载地址 ：http://music.163.com/song/media/outer/url?id={}.mp3
     *
     * @param musicList
     */
    @Override
    public void download(List<Music> musicList) {
        if (CollUtil.isEmpty(musicList)) {
            log.info("带下载列表为空");
            return;
        }
        ArrayList<DownloadRequest> requestList = new ArrayList<>(musicList.size());
        for (Music music : musicList) {
            String url = String.format(DOWNLOAD_URL, music.getId());
            StringBuilder fileName = new StringBuilder();
            for (Artist artist : music.getArtists()) {
                fileName.append(artist.getName()).append("-");
            }
            fileName.append(music.getName()).append(".mp3");
            String replace = fileName.toString()
                    .replace('/', '-')
                    .replace('\\', '-');
            requestList.add(new DownloadRequest(url, replace, path));
        }
        List<List<DownloadRequest>> partition = Lists.partition(requestList, 15);
        List<CompletableFuture<Boolean>> futures = new ArrayList<>(partition.size());
        for (List<DownloadRequest> requests : partition) {
            CompletableFuture<Boolean> future = CompletableFuture.supplyAsync(() -> {
                requests.forEach(DownloadUtils::downloadFile);
                return true;
            }, taskExecutor);
            futures.add(future);
        }
        futures.forEach(CompletableFuture::join);
        log.info("下载完毕");
    }

    public void downloadSingleThreaded(List<Music> musicList) {
        if (CollUtil.isEmpty(musicList)) {
            log.info("带下载列表为空");
            return;
        }
        ArrayList<DownloadRequest> requestList = new ArrayList<>(musicList.size());
        for (Music music : musicList) {
            String url = String.format(DOWNLOAD_URL, music.getId());
            StringBuilder fileName = new StringBuilder();
            for (Artist artist : music.getArtists()) {
                fileName.append(artist.getName()).append("-");
            }
            fileName.append(music.getName()).append(".mp3");
            String replace = fileName.toString()
                    .replace('/', '-')
                    .replace('\\', '-');
            requestList.add(new DownloadRequest(url, replace, path));
        }
        if (requestList.size() > 0) {
            requestList.forEach(DownloadUtils::downloadFile);
        }
    }
}
