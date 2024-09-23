package com.example.elastic.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * @author iumyxF
 * @description:
 * @date 2023/6/2 14:04
 * <p>
 * es和mysql关系
 * index-索引 等同于 一个数据库database
 * type-类型  等同于 一张表
 * documents-文档 等同于 一条数据（一行）
 * fields-字段    等同于 一列数据（）
 * <p>
 * Document注解作用在类上，标记实体类为文档对象，常用属性如下：
 * （1）indexName：对应索引库名称；
 * （2）type：对应在索引库中的类型；
 * （3）shards：分片数
 * （4）replicas：副本数；
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "person")
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * （1）@Id：作用在成员变量，标记一个字段为id主键；一般id字段或是域不需要存储也不需要分词；
     * （2）type：字段的类型，取值是枚举，FieldType；
     * FieldType.Keyword存储字符串数据时，不会建立索引。不进行分词，直接索引
     * FieldType.Text在存储字符串数据的时候，会自动建立索引，也会占用部分空间资源。支持分词，全文检索
     * （3）index：是否索引，布尔值类型，默认是true；
     * （4）store：是否存储，布尔值类型，默认值是false；
     * （5）analyzer：分词器名称
     */
    @Id
    @Field(type = FieldType.Long)
    private Long id;

    private String firstname;

    private String lastname;

    private Integer age;

    private String gender;
}
