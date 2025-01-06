package com.example.design.demo.pull.commons;

import lombok.Data;

/**
 * @author fzy
 * @description: 发起人员信息上下文
 * @date 6/1/2025 下午3:44
 */
@Data
public class SyncPersonInfoContext {

    private Long id;

    /**
     * 同步方式
     * 1 - 钉钉
     * 2 - 微信
     */
    private Integer type;
}
