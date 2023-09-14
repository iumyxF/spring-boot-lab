package com.example.elastic.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.util.Arrays;
import java.util.List;

/**
 * @author iumyxF
 * @description:
 * @date 2023/6/3 10:27
 */
@Document(indexName = "blog")
public class Article {

    @Id
    private String id;

    @MultiField(mainField = @Field(type = FieldType.Text, fielddata = true),
            otherFields = {@InnerField(suffix = "verbatim", type = FieldType.Keyword)})
    private String title;

    /**
     * 标记为嵌套类型字段
     */
    @Field(type = FieldType.Nested, includeInParent = true)
    private List<Author> authors;

    @Field(type = FieldType.Keyword)
    private String[] tags;

    public Article() {
    }

    public Article(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String... tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Article{" + "id='" + id + '\'' + ", title='" + title + '\'' + ", authors=" + authors + ", tags=" + Arrays.toString(tags) + '}';
    }
}
