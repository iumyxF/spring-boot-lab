package com.example.crawler.service.impl;

import com.example.crawler.model.search.Music;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

/**
 * @author iumyxF
 * @description:
 * @date 2023/12/19 10:10
 */
public class NetEaseMusicServiceImplTest {
    @Mock
    Logger log;
    @InjectMocks
    NetEaseMusicServiceImpl netEaseMusicServiceImpl;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * 单线程 100首下载时间29秒
     */
    @Test
    public void testSearchMusic() {
        //Spring 提供ReflectionTestUtils 工具类解决value值null的情况
        ReflectionTestUtils.setField(netEaseMusicServiceImpl, "path", "F:\\testData\\crawler");
        List<Music> result = netEaseMusicServiceImpl.searchMusic(1, 100, "周杰伦");
        long startTime = System.currentTimeMillis();
        netEaseMusicServiceImpl.downloadSingleThreaded(result);
        long endTime = System.currentTimeMillis();
        System.out.println("下载歌曲所耗费时间 : " + ((endTime - startTime) / 1000) + "秒");
    }
}