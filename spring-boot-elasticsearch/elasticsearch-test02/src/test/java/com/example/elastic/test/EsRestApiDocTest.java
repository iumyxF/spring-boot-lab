package com.example.elastic.test;

import cn.hutool.http.HttpRequest;
import org.junit.jupiter.api.Test;

/**
 * @author fzy
 * @description: ES 文档操作
 * @date 2024/9/23 9:48
 */
public class EsRestApiDocTest {

    private final static String ES_SERVER_URL = "http://192.168.2.180:9200";

    @Test
    public void queryAll() {
        // GET /{索引库名}/_search
        String resp = HttpRequest.get(ES_SERVER_URL + "/userinfo/_search")
                .execute()
                .body();
        System.out.println(resp);
    }

    @Test
    public void queryById() {
        // GET /{索引库名}/_doc/{id}
        String resp = HttpRequest.get(ES_SERVER_URL + "/userinfo/_doc/1")
                .execute()
                .body();
        System.out.println(resp);
    }

    /**
     * 返回文档的字段处理
     * <a href="https://www.elastic.co/guide/en/elasticsearch/reference/7.17/docs-get.html"/>
     */
    @Test
    public void queryFiled() {
        // 不返回文档 selectCount?
        String notSource = HttpRequest.get(ES_SERVER_URL + "/userinfo/_doc/1?_source=false")
                .execute()
                .body();
        System.out.println(notSource);

        // 只获取info字段
        String incResp = HttpRequest.get(ES_SERVER_URL + "/userinfo/_doc/1?_source_includes=info")
                .execute()
                .body();
        System.out.println(incResp);
        // 不获取info字段
        String excResp = HttpRequest.get(ES_SERVER_URL + "/userinfo/_doc/1?_source_excludes=info")
                .execute()
                .body();
        System.out.println(excResp);
    }

    @Test
    public void insert() {
        // POST /{索引库名}/_doc/{文档id}
        String resp = HttpRequest.post(ES_SERVER_URL + "/userinfo/_doc/1")
                .body("{\"info\":\"我叫张三\",\"email\":\"zhangsan@qq.com\",\"name\":{\"firstName\":\"张\",\"lastName\":\"三\"}}")
                .execute()
                .body();
        System.out.println(resp);
    }

    /**
     * 修改文档两种方式
     * 1. 全量修改：完全覆盖原来的文档
     * 2. 增量修改：修改文档中的部分字段
     */
    @Test
    public void fullEdit() {
        // 全量修改 PUT /{索引库名}/_doc/{文档id}
        String resp = HttpRequest.put(ES_SERVER_URL + "/userinfo/_doc/1")
                // 省略了email属性
                .body("{\"info\":\"我叫李四\",\"name\":{\"firstName\":\"李\",\"lastName\":\"四\"}}")
                .execute()
                .body();
        System.out.println(resp);
    }

    @Test
    public void incrEdit() {
        // POST /{索引库名}/_update/{文档id} ,注意请求体是doc对象不是文档对象
        String resp = HttpRequest.post(ES_SERVER_URL + "/userinfo/_update/1")
                .body("{\"doc\":{\"email\":\"lisi@qq.com\"}}")
                .execute()
                .body();
        System.out.println(resp);
    }

    @Test
    public void delete() {
        // DELETE /{索引库名}/_doc/{文档id}
        String resp = HttpRequest.delete(ES_SERVER_URL + "/userinfo/_doc/1")
                .execute()
                .body();
        System.out.println(resp);
    }

}