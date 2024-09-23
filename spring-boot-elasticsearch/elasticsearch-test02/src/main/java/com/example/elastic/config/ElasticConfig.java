package com.example.elastic.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author fzy
 * @description:
 * @date 2024/9/23 14:56
 */
@Configuration
public class ElasticConfig {

    /**
     * 新版本推荐使用方式
     * <a href="https://www.elastic.co/guide/en/elasticsearch/client/java-api-client/7.17/connecting.html">...</a>
     *
     * @return ElasticsearchClient
     */
    @Bean
    public ElasticsearchClient elasticsearchClient() {
        RestClient client = RestClient
                .builder(new HttpHost("192.168.2.180", 9200))
                .build();
        ElasticsearchTransport transport = new RestClientTransport(client, new JacksonJsonpMapper());
        return new ElasticsearchClient(transport);
    }
}
