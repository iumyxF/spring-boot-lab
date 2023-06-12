package com.example.elastic.repository;

import com.example.elastic.entities.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * The interface Article repository.
 *
 * @author fzy
 * @description: query语句中的?0是占位符
 * @date 2023 /6/3 10:27
 */
public interface ArticleRepository extends ElasticsearchRepository<Article, String> {

    /**
     * Find by authors name page.
     *
     * @param name     the name
     * @param pageable the pageable
     * @return the page
     */
    Page<Article> findByAuthorsName(String name, Pageable pageable);

    /**
     * Find by authors name using custom query page.
     *
     * @param name     the name
     * @param pageable the pageable
     * @return the page
     */
    @Query("{\"bool\": {\"must\": [{\"match\": {\"authors.name\": \"?0\"}}]}}")
    Page<Article> findByAuthorsNameUsingCustomQuery(String name, Pageable pageable);

    /**
     * Find by filtered tag query page.
     * json 含义 布尔查询tags（list）中包含 xx的数据
     *
     * @param tag      the tag
     * @param pageable the pageable
     * @return the page
     */
    @Query("{\"bool\": {\"must\": {\"match_all\": {}}, \"filter\": {\"term\": {\"tags\": \"?0\" }}}}")
    Page<Article> findByFilteredTagQuery(String tag, Pageable pageable);

    /**
     * Find by authors name and filtered tag query page.
     * json 含义 查询作者名字为xx而且tags包含xx的数据
     *
     * @param name     the name
     * @param tag      the tag
     * @param pageable the pageable
     * @return the page
     */
    @Query("{\"bool\": {\"must\": {\"match\": {\"authors.name\": \"?0\"}}, \"filter\": {\"term\": {\"tags\": \"?1\"}}}}")
    Page<Article> findByAuthorsNameAndFilteredTagQuery(String name, String tag, Pageable pageable);
}
