package com.example.sign.mapper;

import com.example.sign.constants.AccessConstants;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 存放分发的密钥DAO
 * @Date 2023/3/8 14:22
 * @author iumyxF
 */
@Repository
public class AccessMapper implements InitializingBean {

    private final HashMap<String, String> secretMap = new HashMap<>(16);

    @Override
    public void afterPropertiesSet() {
        secretMap.put("abc", "123");
        secretMap.put("def", "456");
        secretMap.put("ghi", "789");
    }

    /**
     * 校验AppKey是否存在
     *
     * @param appKey
     * @return
     */
    public String checkAppKeyExist(String appKey) {
        return secretMap.containsKey(appKey) ? AccessConstants.APP_KEY_EXIST : AccessConstants.APP_KEY_NON_EXIST;
    }

    /**
     * 根据AppKey获取AppSecret
     *
     * @param appKey
     * @return AppSecret
     */
    public String getSecretByKey(String appKey) {
        return secretMap.get(appKey);
    }
}
