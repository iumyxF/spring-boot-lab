package com.example.mq.message;

import lombok.Data;

import java.io.Serializable;

/**
 * @description: 消息实体类
 * @Date 2023/2/21 14:34
 * @Author fzy
 */
@Data
public class MessageEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String QUEUE = "QUEUE_DEMO_01";

    public static final String EXCHANGE = "EXCHANGE_DEMO_01";

    public static final String ROUTING_KEY = "ROUTING_KEY_01";

    /**
     * 编号
     */
    private Integer id;

}
