package com.example.personnel.sync;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.personnel.model.dto.Credential;
import com.example.personnel.model.dto.ExternalConfig;
import com.example.personnel.model.dto.QyWechatExternalConfig;

import java.util.Objects;

/**
 * @author iumyxF
 * @description:
 * @date 2024/6/28 11:10
 */
public class QyWechatSyncServiceImpl extends AbstractSyncTemplate {

    private static final String GET_ACCESS_TOKEN_URL = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=%s&corpsecret=%s";

    private static final String GET_USER_ID_LIST = "https://qyapi.weixin.qq.com/cgi-bin/user/list_id?access_token=%s";

    private static final String GET_USER_BY_ID = "https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token=%s&userid=%s";

    private static final Integer ACCESS_TOKEN_EXPIRE_TIME_ERROR_CODE = 40014;

    @Override
    protected ExternalConfig getExternalConfig() {
        return QyWechatExternalConfig.builder()
                // 企业ID
                .corpId("xxx")
                // 通讯录应用Secret
                .corpSecret("xxx")
                .build();
    }

    /**
     * 优化：
     * 1. 返回null的地方应该抛出异常信息，这里简化了
     * 2. 加入缓存，不用每次通过http来获取凭证
     *
     * @param config
     * @return
     */
    @Override
    protected Credential getCredential(ExternalConfig config) {
        String response = HttpUtil.get(String.format(GET_ACCESS_TOKEN_URL, config.getCorpId(), config.getCorpSecret()));
        if (StrUtil.isEmpty(response)) {
            return null;
        }
        JSONObject jsonObject = JSONUtil.parseObj(response);
        if (Objects.equals(jsonObject.getInt("errcode"), 0)) {
            return null;
        }
        String accessToken = jsonObject.getStr("access_token");
        // 正常情况下为7200秒（2小时）
        Long expiresIn = jsonObject.getLong("expires_in");
        return new Credential(accessToken, System.currentTimeMillis() + expiresIn * 1000L);
    }

    /**
     * 重试机制，使用消息队列，把失败的信息放入队列，定时任务轮询队列，进行重试
     */
    @Override
    protected void syncUser(Credential credential) {
        //HttpUtil.get(String.format(GET_USER_BY_ID, ));

    }

    public static void main(String[] args) {
        double d1 = -0.5;
        System.out.println("Ceil d1=" + Math.ceil(d1));
        System.out.println("floor d1=" + Math.floor(d1));
    }

}