package com.example.elastic.test;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author fzy
 * @description: 使用HTTP测试ES
 * @date 2024/9/21 16:30
 */
public class EsRestApiTest {

    /*
    Springboot + ES: https://docs.spring.io/spring-data/elasticsearch/reference/index.html
    ES: https://www.elastic.co/guide/en/elasticsearch/reference/current/eql.html#eql-advantages

    增 单个 批量
    删 主键单个 主键批量 按条件
    改 修改单个属性 修改全部属性（除了主键）
    查 主键查询 主键列表查询 条件查询 分页查询 分页条件查询
     */

    private final static String ES_SERVER_URL = "http://localhost:9200";

    @Test
    public void restApiInsert() {
        /**
         * 请求获取按账号排序的银行（bank）索引中的所有文档：
         * GET /bank/_search
         * {
         *   "query": { "match_all": {} },
         *   "sort": [
         *     { "account_number": "asc" }
         *   ]
         * }
         */
        HashMap<String, Object> params = new HashMap<>(16);
        params.put("query", MapUtil.of("match_all", "{}"));
        params.put("sort", ListUtil.of(MapUtil.of("account_number", "asc")));
        System.out.println("请求参数 = " + params);
        String url = ES_SERVER_URL + "/bank/_search";
        String response = HttpUtil.get(url, params);
        System.out.println("响应结果 = " + response);
    }

}
