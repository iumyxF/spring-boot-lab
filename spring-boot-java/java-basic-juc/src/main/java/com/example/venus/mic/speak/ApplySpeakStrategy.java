package com.example.venus.mic.speak;

import java.util.ArrayList;
import java.util.List;

/**
 * @author iumyx
 * @description:
 * @date 2024/3/8 16:37
 */
public class ApplySpeakStrategy implements SpeakStrategy{

    private final List<Integer> openQueue;

    private final List<Integer> waitQueue;

    private final Integer size;

    public ApplySpeakStrategy(Integer size) {
        this.openQueue = new ArrayList<>(size);
        this.waitQueue = new ArrayList<>(size);
        this.size = size;
    }

    @Override
    public void speak() {
        //判断是否已满、重复添加等
        System.out.println("ApplySpeakStrategy 校验条件...");
        //加入到等待发言队列
        System.out.println("ApplySpeakStrategy 话筒成功加入到等待队列，等待批准...");
    }

    @Override
    public void close() {

    }

    @Override
    public void approve() {
        //将等待的话筒移动到发言中
        System.out.println("ApplySpeakStrategy 话筒批准发言...");
        //发送开启话筒mqtt
    }
}
