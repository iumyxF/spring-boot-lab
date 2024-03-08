package com.example.venus.mic.speak;

import java.util.ArrayList;

/**
 * @author fzy
 * @description:
 * @date 2024/3/8 16:24
 */
public class WaitSpeakStrategy implements SpeakStrategy {

    private final ArrayList<Integer> openQueue;

    private final Integer size;

    public WaitSpeakStrategy(Integer size) {
        this.openQueue = new ArrayList<>(size);
        this.size = size;
    }

    @Override
    public void speak() {
        //判断队列是否满
        System.out.println("WaitSpeakStrategy 校验条件...");
        //发送开咪mqtt

        //检查是否返回

        //加入stack
        System.out.println("WaitSpeakStrategy 话筒发言成功");
    }

    @Override
    public void close() {

    }

    @Override
    public void approve() {

    }
}
