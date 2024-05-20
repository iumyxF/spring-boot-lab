package com.example.docs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * Knife4j 接口文档配置
 * <a href="https://doc.xiaominfo.com/knife4j/documentation/get_start.html">...</a>
 *
 * @author iumyxF
 */
@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfig {

    /**
     * 通过knife4j生成接口文档
     */
    @Bean(value = "adminApi")
    public Docket adminDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .host("https://www.baidu.com")
                .apiInfo(apiInfo())
                .groupName("test")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.docs.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("spring-docs 项目接口文档")
                .description("# spring-docs 项目接口文档")
                .version("1.0.0")
                .build();
    }
}