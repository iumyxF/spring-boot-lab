package com.example.vertx.common.model;

import lombok.Data;

/**
 * @author iumyxF
 * @description: 服务注册信息
 * @date 2024/5/14 11:45
 */
@Data
public class ServiceMetaInfo {

    /**
     * 服务名称
     */
    private String serviceName;

    /**
     * 服务版本号
     */
    private String serviceVersion = RpcConstant.DEFAULT_SERVICE_VERSION;

    /**
     * 服务域名
     */
    private String serviceHost;

    /**
     * 服务端口号
     */
    private Integer servicePort;

    /**
     * 服务分组 默认分组
     */
    private String serviceGroup = RpcConstant.DEFAULT_GROUP;

    /**
     * 获取服务键名
     *
     * @return serviceKey = serviceName:serviceVersion
     */
    public String getServiceKey() {
        return String.format("%s:%s", serviceName, serviceVersion);
    }

    /**
     * 获取服务注册节点键名
     *
     * @return nodeKey = serviceName:serviceVersion/serviceHost:servicePort
     */
    public String getServiceNodeKey() {
        return String.format("%s/%s:%s", getServiceKey(), serviceHost, servicePort);
    }
}