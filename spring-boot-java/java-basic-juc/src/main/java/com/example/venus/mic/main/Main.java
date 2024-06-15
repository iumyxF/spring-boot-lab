package com.example.venus.mic.main;

import com.example.venus.mic.control.MicTypeAControlTemplate;
import com.example.venus.mic.control.MicTypeBControlTemplate;
import com.example.venus.mic.speak.ApplySpeakStrategy;
import com.example.venus.mic.speak.WaitSpeakStrategy;

/**
 * @author iumyx
 * @description:
 * @date 2024/3/8 16:31
 */
public class Main {
    public static void main(String[] args) {
        ApplySpeakStrategy applySpeakStrategy = new ApplySpeakStrategy(10);
        WaitSpeakStrategy speakStrategy = new WaitSpeakStrategy(10);

        MicTypeAControlTemplate at = new MicTypeAControlTemplate(applySpeakStrategy);
        MicTypeBControlTemplate bt = new MicTypeBControlTemplate(speakStrategy);

        at.open();

        System.out.println("#############");

        bt.open();

    }
}
