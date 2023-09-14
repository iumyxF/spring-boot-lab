package com.example.mock.service;

import com.example.mock.dao.DemoDao;

/**
 * @author iumyxF
 * @description: Mock 测试
 * @date 2023/5/22 10:21
 */
public class DemoService {

    private DemoDao demoDao;

    public DemoService(DemoDao demoDao) {
        this.demoDao = demoDao;
    }

    public int getDemoStatus() {
        return demoDao.getDemoStatus();
    }

    public static int add(int a, int b) {
        return a + b;
    }
}
