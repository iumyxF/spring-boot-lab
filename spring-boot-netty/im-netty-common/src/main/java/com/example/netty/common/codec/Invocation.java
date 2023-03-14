package com.example.netty.common.codec;

import com.alibaba.fastjson.JSON;
import com.example.netty.common.dispatcher.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @description: 消息传输协议实体
 * @Date 2023/3/14 10:58
 * @Author fzy
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Invocation {

    /**
     * 类型
     */
    private String type;
    /**
     * 消息，JSON 格式
     */
    private String message;

    public Invocation(String type, Message message) {
        this.type = type;
        this.message = JSON.toJSONString(message);
    }
}
