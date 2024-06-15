package com.example.aop.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 日志操作持久化对象
 * @Date 2023/2/13 10:48
 * @author iumyxF
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperationLog {

    /**
     * 主键
     */
    private Long opId;

    /**
     * 请求方法
     */
    private String method;

    /**
     * 请求方式
     */
    private String requestMethod;

    /**
     * 操作地址
     */
    private String opIp;

    public Long getOpId() {
        return opId;
    }

    public OperationLog setOpId(Long opId) {
        this.opId = opId;
        return this;
    }

    public String getMethod() {
        return method;
    }

    public OperationLog setMethod(String method) {
        this.method = method;
        return this;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public OperationLog setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
        return this;
    }

    public String getOpIp() {
        return opIp;
    }

    public OperationLog setOpIp(String opIp) {
        this.opIp = opIp;
        return this;
    }
}
