package com.example.elastic.test;

import cn.hutool.http.HttpRequest;
import org.junit.Test;

/**
 * @author fzy
 * @description: 索引的rest api
 * @date 2024/9/21 17:28
 */
public class EsRestApiIndexTest {

    /**
     * es index 等价与 mysql 的 database
     */
    private final static String ES_SERVER_URL = "http://localhost:9200";

    @Test
    public void queryAllIndex() {
        String response = HttpRequest.get(ES_SERVER_URL + "/_cat/indices")
                .basicAuth("elastic", "123456")
                .execute()
                .body();
        System.out.println("响应结果 = " + response);
    }

    @Test
    public void insert() {
        String myIndex = "userinfo";
        String resp = HttpRequest.put(ES_SERVER_URL + "/" + myIndex)
                .basicAuth("elastic", "123456")
                .execute()
                .body();
        // {"acknowledged":true,"shards_acknowledged":true,"index":"userinfo"}
        System.out.println(resp);
    }

    @Test
    public void delete() {

    }

    @Test
    public void edit() {

    }

}
