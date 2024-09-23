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
     * 索引（index） ==> 一张表（table），如商品索引、用户索引、订单索引等...
     * 映射（mapping）==> schema  ，索引中文档的字段约束信息，类似表的结构约束
     * 文档（documents ） ==> 一行数据
     * 字段（fields） ==> 一个属性
     */
    private final static String ES_SERVER_URL = "http://192.168.2.180:9200";

    @Test
    public void queryAllIndex() {
        // GET /_cat/indices
        String response = HttpRequest.get(ES_SERVER_URL + "/_cat/indices")
                .execute()
                .body();
        System.out.println(response);
    }

    @Test
    public void query() {
        String response = HttpRequest.get(ES_SERVER_URL + "/userinfo")
                .execute()
                .body();
        System.out.println(response);
    }

    @Test
    public void insert() {
        // PUT /${indexName}
        String resp = HttpRequest.put(ES_SERVER_URL + "/userinfo")
                .body("{\"mappings\":{\"properties\":{\"info\":{\"type\":\"text\",\"analyzer\":\"smartcn\"},\"email\":{\"type\":\"keyword\",\"index\":false},\"name\":{\"type\":\"object\",\"properties\":{\"firstName\":{\"type\":\"text\",\"index\":false},\"lastName\":{\"type\":\"text\",\"index\":false}}}}}}")
                .execute()
                .body();
        // {"acknowledged":true,"shards_acknowledged":true,"index":"userinfo"}
        System.out.println(resp);
    }

    @Test
    public void delete() {
        // DELETE /${indexName}
        String resp = HttpRequest.delete(ES_SERVER_URL + "/userinfo")
                .execute()
                .body();
        System.out.println(resp);
    }

    @Test
    public void edit() {
        // es 不支持修改索引库，只能在索引库中增加字段
        // PUT /${indexName}/_mappings
        String resp = HttpRequest.put(ES_SERVER_URL + "/userinfo/_mappings")
                .body("{\"properties\":{\"gender\":{\"type\":\"boolean\"}}}")
                .execute()
                .body();
        System.out.println(resp);
    }

}
