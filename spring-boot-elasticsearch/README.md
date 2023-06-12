# elasticsearch

## 查询

链接

1. https://www.elastic.co/guide/cn/elasticsearch/guide/current/query-dsl-intro.html
2. https://n3xtchen.github.io/n3xtchen/elasticsearch/2017/07/05/elasticsearch-23-useful-query-example

书文档信息的集合（有以下字段：title（标题）, authors（作者）, summary（摘要）, publish_date（发布日期）和 num_reviews（浏览数）

### 简单查询(query)

query用于基本的匹配查询

#### 简单匹配查询

```json
{
  "query": {
    "match": {
      "title": "param"
    }
  }
}
```

#### 多字段匹配

查询title和summary包含"elasticsearch guide"的文档

```json
{
  "query": {
    "multi_match": {
      "query": "elasticsearch guide",
      "fields": [
        "title",
        "summary"
      ]
    }
  }
}
```

###### 多字段查询提高不同字段的权重查询

将summary字段的权重提高三倍

```json
{
  "query": {
    "multi_match": {
      "query": "elasticsearch guide",
      "fields": [
        "title",
        "summary^3"
      ]
    }
  },
  "_source": [
    "title",
    "summary",
    "publish_date"
  ]
}
```

### 布尔查询

为了提供更相关或者特定的结果，AND/OR/NOT 操作符可以用来调整我们的查询。

- must 等同于 AND
- must_not 等同于 NOT
- should 等同于 OR

#### 简单布尔查询

title包含ElasticSearch 或者（OR） Solr ，
并且（AND）它的作者是 Clinton Gormley 不是（NOT）Radu Gheorge
```json
{
  "query": {
    "bool": {
      "must": {
        "bool": {
          "should": [
            {
              "match": {
                "title": "Elasticsearch"
              }
            },
            {
              "match": {
                "title": "Solr"
              }
            }
          ]
        }
      },
      "must": {
        "match": {
          "authors": "clinton gormely"
        }
      },
      "must_not": {
        "match": {
          "authors": "radu gheorge"
        }
      }
    }
  }
}
```

## SpringBoot中的使用

- 接口应该是实现ElasticsearchRepository是比较规范的(大概)