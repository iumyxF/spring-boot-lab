package com.example.crawler.service;

import com.example.crawler.model.search.Music;

import java.util.List;

/**
 * @author iumyxF
 * @description:
 * @date 2023/12/19 9:46
 */
public interface IMusicService {

    /**
     * 查询歌曲
     *
     * @param page 页数
     * @param size 每页数量
     * @param key  歌名/歌手/专辑名
     * @return 结果
     */
    List<Music> searchMusic(Integer page, Integer size, String key);

    void download(List<Music> musicList);
}
