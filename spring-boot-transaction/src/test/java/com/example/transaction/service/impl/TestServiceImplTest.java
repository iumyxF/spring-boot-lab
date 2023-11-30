package com.example.transaction.service.impl;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author fzy
 * @description:
 * @date 2023/11/29 14:13
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestServiceImplTest {

    @Resource
    TestServiceImpl testService;

    @Test
    public void testDoServiceVersion1() {
        testService.doServiceVersion1();
    }

    @Test
    public void testDoServiceVersion2() {
        testService.doServiceVersion2();
    }

    @Test
    public void testDoServiceVersion3() {
        testService.doServiceVersion3();
    }

    @Test
    public void testThreadDoService() {
        testService.threadDoService();
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme