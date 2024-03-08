package com.example.venus.mic.control;

import com.example.venus.mic.speak.SpeakStrategy;

/**
 * @author fzy
 * @description:
 * @date 2024/3/8 16:26
 */
public class MicTypeBControlTemplate extends MicControlTemplate {

    private final SpeakStrategy speakStrategy;

    public MicTypeBControlTemplate(SpeakStrategy speakStrategy) {
        this.speakStrategy = speakStrategy;
    }

    @Override
    protected void speak() {
        System.out.println("话筒类型B 打开话筒...");
        speakStrategy.speak();
        System.out.println("话筒类型B 打开话筒完毕...");
    }
}
