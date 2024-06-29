package com.example.personnel.model.enums;

/**
 * @author iumyxF
 * @description: 来源枚举
 * @date 2024/6/28 9:19
 */
public enum SourceEnum {

    /**
     * 系统
     */
    SYSTEM(0, "系统"),

    /**
     * 企业微信
     */
    QY_WECHAT(1, "企业微信"),

    /**
     * 钉钉
     */
    DING_TALK(2, "钉钉"),
    ;

    private final Integer source;

    private final String sourceName;

    SourceEnum(Integer source, String sourceName) {
        this.source = source;
        this.sourceName = sourceName;
    }

    public Integer getSource() {
        return source;
    }

    public String getSourceName() {
        return sourceName;
    }
}
