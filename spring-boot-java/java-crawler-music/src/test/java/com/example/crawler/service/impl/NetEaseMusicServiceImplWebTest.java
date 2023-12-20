package com.example.crawler.service.impl;

import com.example.crawler.model.search.Music;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

/**
 * @author iumyxF
 * @description:
 * @date 2023/12/20 11:45
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class NetEaseMusicServiceImplWebTest {

    @Autowired
    private NetEaseMusicServiceImpl netEaseMusicServiceImpl;

    /**
     * 多线程 100首下载时间16秒
     */
    @Test
    public void testSearchMusic() {
        //Spring 提供ReflectionTestUtils 工具类解决value值null的情况
        ReflectionTestUtils.setField(netEaseMusicServiceImpl, "path", "F:\\testData\\crawler");
        List<Music> result = netEaseMusicServiceImpl.searchMusic(1, 100, "周杰伦");
        long startTime = System.currentTimeMillis();
        netEaseMusicServiceImpl.download(result);
        long endTime = System.currentTimeMillis();
        System.out.println("下载歌曲所耗费时间 : " + ((endTime - startTime) / 1000) + "秒");
    }
}
