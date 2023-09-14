package com.example.mock.dao;

import java.util.Random;

/**
 * @author iumyxF
 * @description: Mock 测试
 * @date 2023/5/22 10:21
 */
public class DemoDao {

    public int getDemoStatus() {
        return new Random().nextInt();
    }

}
