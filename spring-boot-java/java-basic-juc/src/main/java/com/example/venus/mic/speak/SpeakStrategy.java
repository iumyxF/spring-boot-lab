package com.example.venus.mic.speak;

/**
 * @author iumyx
 * @description:
 * @date 2024/3/8 16:23
 */
public interface SpeakStrategy {

    void speak();

    void close();

    void approve();

}
