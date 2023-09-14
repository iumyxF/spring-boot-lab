package com.example.elastic.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * @author iumyxF
 * @description:
 * @date 2023/6/3 10:28
 */
@Configuration
@EnableElasticsearchRepositories(basePackages = "com.example.elastic.repository")
@ComponentScan(basePackages = {"com.example.elastic.repository"})
public class ElasticConfig extends AbstractElasticsearchConfiguration {

    /**
     * 注入一个简单的RestHighLevelClient容器
     */
    @Bean
    @Override
    public RestHighLevelClient elasticsearchClient() {
        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("localhost:9200")
                .withBasicAuth("elastic", "123456")
                .build();
        return RestClients.create(clientConfiguration).rest();
    }
}
