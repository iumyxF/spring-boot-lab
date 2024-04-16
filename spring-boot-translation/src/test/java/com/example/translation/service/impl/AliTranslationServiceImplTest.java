package com.example.translation.service.impl;

import com.aliyun.alimt20181012.models.GetBatchTranslateResponse;
import com.aliyun.tea.TeaException;


/**
 * @author fzy
 * @description: 阿里云测试代码
 * @date 2024/4/16 14:24
 */
public class AliTranslationServiceImplTest {

    /**
     * 使用AK&SK初始化账号Client
     *
     * @return Client
     * @throws Exception
     */
    public com.aliyun.alimt20181012.Client createClient() throws Exception {
        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config()
                .setAccessKeyId("xx")
                .setAccessKeySecret("xxx");
        config.endpoint = "mt.aliyuncs.com";
        return new com.aliyun.alimt20181012.Client(config);
    }

    public void testExample() throws Exception {
        com.aliyun.alimt20181012.Client client = createClient();
        com.aliyun.alimt20181012.models.GetBatchTranslateRequest getBatchTranslateRequest = new com.aliyun.alimt20181012.models.GetBatchTranslateRequest()
                .setFormatType("text")
                .setTargetLanguage("zh")
                .setSourceLanguage("en")
                .setScene("general")
                .setApiType("translate_standard")
                .setSourceText("{\"11\":\"hello boy\",\"12\":\"go home\",\"13\":\"we can\"}");
        com.aliyun.teautil.models.RuntimeOptions runtime = new com.aliyun.teautil.models.RuntimeOptions();
        try {
            GetBatchTranslateResponse response = client.getBatchTranslateWithOptions(getBatchTranslateRequest, runtime);
            System.out.println(response);
        } catch (TeaException error) {
            System.out.println(error.getMessage());
            System.out.println(error.getData().get("Recommend"));
            com.aliyun.teautil.Common.assertAsString(error.message);
        } catch (Exception _error) {
            TeaException error = new TeaException(_error.getMessage(), _error);
            System.out.println(error.getMessage());
            System.out.println(error.getData().get("Recommend"));
            com.aliyun.teautil.Common.assertAsString(error.message);
        }
    }
}