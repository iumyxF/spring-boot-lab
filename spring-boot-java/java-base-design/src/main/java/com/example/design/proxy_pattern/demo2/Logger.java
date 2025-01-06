package com.example.design.proxy_pattern.demo2;

/**
 * @author fzy
 * @description:
 * @date 2024/6/28 10:27
 */
public class Logger {

    public void Log(String userId) {
        System.out.println("更新数据库，用户'" + userId + " 查询次数加1！");
    }
}
