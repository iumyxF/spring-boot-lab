package com.example.practice.leetcode.business.pull.user;

/**
 * @author fzy
 * @description:
 * @date 3/1/2025 下午3:54
 */
public class ThirdPartyServiceFactory {

    public static ThirdPartyService createService(String type) {
        if ("dingTalk".equals(type)) {
            return new DingTalkService();
        } else if ("wechat".equals(type)) {
            return new WechatService();
        } else {
            throw new IllegalArgumentException("Unsupported third-party type: " + type);
        }
    }
}
