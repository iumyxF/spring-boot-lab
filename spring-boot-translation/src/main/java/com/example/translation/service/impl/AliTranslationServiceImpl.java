package com.example.translation.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson2.JSON;
import com.aliyun.alimt20181012.Client;
import com.aliyun.alimt20181012.models.GetBatchTranslateRequest;
import com.aliyun.alimt20181012.models.GetBatchTranslateResponse;
import com.aliyun.alimt20181012.models.GetBatchTranslateResponseBody;
import com.aliyun.tea.TeaException;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.teautil.models.RuntimeOptions;
import com.example.translation.entities.Word;
import com.example.translation.service.TranslationService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.aliyun.teautil.Common.assertAsString;

/**
 * @author iumyx
 * @description: 阿里翻译
 * @date 2024/4/16 13:44
 */
public class AliTranslationServiceImpl implements TranslationService {

    private static final String accessKeyId = "xxx";

    private static final String accessKeySecret = "xxx";

    public Client createClient() {
        Config config = new Config()
                .setAccessKeyId(accessKeyId)
                .setAccessKeySecret(accessKeySecret);
        config.endpoint = "mt.aliyuncs.com";
        try {
            return new Client(config);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Word> getBatchTranslate(List<Word> words, String sourceLanguage, String targetLanguage) {
        // 创建单词下标map 用于后续查询
        Map<String, Word> wordMap = words.stream().collect(Collectors.toMap(Word::getIndex, word -> word));
        // 创建请求client
        Client client = createClient();
        // 创建请求json
        String json = createSourceText(words);
        GetBatchTranslateRequest getBatchTranslateRequest = new GetBatchTranslateRequest()
                .setFormatType("text")
                .setTargetLanguage(targetLanguage)
                .setSourceLanguage(sourceLanguage)
                .setScene("general")
                .setApiType("translate_standard")
                .setSourceText(json);
        RuntimeOptions runtime = new RuntimeOptions();
        try {
            // 复制代码运行请自行打印 API 的返回值
            GetBatchTranslateResponse response = client.getBatchTranslateWithOptions(getBatchTranslateRequest, runtime);
            //System.out.println(response);
            List<Map<String, ?>> translatedWordList = Optional.of(response)
                    .map(GetBatchTranslateResponse::getBody)
                    .map(GetBatchTranslateResponseBody::getTranslatedList)
                    .orElse(null);
            if (CollUtil.isNotEmpty(translatedWordList)) {
                translatedWordList.forEach(item -> {
                    String index = String.valueOf(item.get("index"));
                    String translated = String.valueOf(item.get("translated"));
                    if (wordMap.containsKey(index)) {
                        Word word = wordMap.get(index);
                        word.setTranslated(translated);
                    }
                });
            }
            return words;
        } catch (TeaException error) {
            // 此处仅做打印展示，请谨慎对待异常处理，在工程项目中切勿直接忽略异常。
            // 错误 message
            System.out.println(error.getMessage());
            // 诊断地址
            System.out.println(error.getData().get("Recommend"));
            assertAsString(error.message);
        } catch (Exception _error) {
            TeaException error = new TeaException(_error.getMessage(), _error);
            // 此处仅做打印展示，请谨慎对待异常处理，在工程项目中切勿直接忽略异常。
            // 错误 message
            System.out.println(error.getMessage());
            // 诊断地址
            System.out.println(error.getData().get("Recommend"));
            assertAsString(error.message);
        }
        return null;
    }

    private String createSourceText(List<Word> words) {
        HashMap<String, String> map = new HashMap<>(words.size());
        words.forEach(w -> map.put(String.valueOf(w.getIndex()), w.getOriginal()));
        return JSON.toJSONString(map);
    }
}