package com.example.elastic.service.impl;

import com.example.elastic.config.ElasticConfig;
import com.example.elastic.entities.Article;
import com.example.elastic.entities.Author;
import com.example.elastic.repository.ArticleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static org.elasticsearch.index.query.Operator.AND;
import static org.elasticsearch.index.query.QueryBuilders.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


/**
 * @author fzy
 * @description:
 * @date 2023/6/3 11:16
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ElasticConfig.class)
public class ArticleServiceImplTest {

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @Autowired
    private ArticleRepository articleRepository;

    private final Author hugo = new Author("维克多·雨果");
    private final Author yuHua = new Author("余华");

    //@Before
    @Test
    public void before() {
        Article article = new Article("文章1");
        article.setAuthors(Arrays.asList(hugo, yuHua));
        article.setTags("elasticsearch", "spring data");
        articleRepository.save(article);

        article = new Article("文章2");
        article.setAuthors(Arrays.asList(hugo));
        article.setTags("search engines", "tutorial");
        articleRepository.save(article);

        article = new Article("文章3");
        article.setAuthors(Arrays.asList(yuHua));
        article.setTags("elasticsearch", "spring data");
        articleRepository.save(article);

        article = new Article("文章4");
        article.setAuthors(Arrays.asList(hugo));
        article.setTags("elasticsearch");
        articleRepository.save(article);
    }

    //@After
    @Test
    public void after() {
        articleRepository.deleteAll();
    }

    /**
     * 测试文章保存时，id会不会自动赋值
     */
    @Test
    public void givenArticleService_whenSaveArticle_thenIdIsAssigned() {
        final List<Author> authors = Arrays.asList(new Author("余华"), yuHua);

        Article article = new Article("测试-1文章");
        article.setAuthors(authors);

        article = articleRepository.save(article);
        assertNotNull(article.getId());
    }

    /**
     * 查询这个作者的文章数量
     * 使用的是内置查询方式
     */
    @Test
    public void givenPersistedArticles_whenSearchByAuthorsName_thenRightFound() {
        final Page<Article> articleByAuthorName = articleRepository
                .findByAuthorsName(yuHua.getName(), PageRequest.of(0, 10));
        assertEquals(2L, articleByAuthorName.getTotalElements());
    }

    /**
     * 查询这个作者的文章数量
     * 使用自定义查询，自己编写Query语句
     * 有些情况下，由于POJO和ES的字段名不一致，导致查询不出结果，这时候就需要自己写Query语句了
     *
     * @link <a href="https://developer.aliyun.com/article/268905">...</a>
     */
    @Test
    public void givenCustomQuery_whenSearchByAuthorsName_thenArticleIsFound() {
        final Page<Article> articleByAuthorName = articleRepository
                .findByAuthorsNameUsingCustomQuery("维克多·雨果", PageRequest.of(0, 10));
        assertEquals(3L, articleByAuthorName.getTotalElements());
    }

    /**
     * 根据标签进行查询文章
     */
    @Test
    public void givenTagFilterQuery_whenSearchByTag_thenArticleIsFound() {
        final Page<Article> articleByAuthorName = articleRepository
                .findByFilteredTagQuery("elasticsearch", PageRequest.of(0, 10));
        System.out.println("*************查询结果*************");
        System.out.println(articleByAuthorName.getContent());
        assertEquals(3L, articleByAuthorName.getTotalElements());
    }

    /**
     * 查询作者名字是[维克多·雨果]而且文章标签是[elasticsearch]的文章
     */
    @Test
    public void givenTagFilterQuery_whenSearchByAuthorsName_thenArticleIsFound() {
        final Page<Article> articleByAuthorName = articleRepository
                .findByAuthorsNameAndFilteredTagQuery("维克多·雨果", "elasticsearch", PageRequest.of(0, 10));
        assertEquals(2L, articleByAuthorName.getTotalElements());
    }

    /**
     * 使用构建器的方式创建自定义查询。
     * 使用NativeSearchQueryBuilder构建查询，查询文章标题拥有关键字“data”的文章列表。
     */
    @Test
    public void givenPersistedArticles_whenUseRegexQuery_thenRightArticlesFound() {
        //正则表达式匹配分词
        final Query searchQuery = new NativeSearchQueryBuilder()
                .withFilter(regexpQuery("title", ".*data.*"))
                .build();

        final SearchHits<Article> articles = elasticsearchOperations
                .search(searchQuery, Article.class, IndexCoordinates.of("blog"));

        assertEquals(1, articles.getTotalHits());
    }

    /**
     * 模糊匹配查询
     */
    @Test
    public void givenSavedDoc_whenTitleUpdated_thenCouldFindByUpdatedTitle() {
        //分词模糊查询
        final Query searchQuery = new NativeSearchQueryBuilder().withQuery(fuzzyQuery("title", "serch"))
                .build();
        final SearchHits<Article> articles = elasticsearchOperations
                .search(searchQuery, Article.class, IndexCoordinates.of("blog"));

        assertEquals(1, articles.getTotalHits());

        final Article article = articles.getSearchHit(0)
                .getContent();
        final String newTitle = "Getting started with Search Engines";
        article.setTitle(newTitle);
        articleRepository.save(article);

        assertEquals(newTitle, articleRepository.findById(article.getId())
                .get()
                .getTitle());
    }

    /**
     * <a href="https://blog.csdn.net/numbbe/article/details/110454270" />
     * 1.分词概念
     * es进行匹配查询先分词再查询，如”万里长城真伟大“ 分词结果：【”万里长城“，”万里“，”长城“，”万“，”里长“，”里“，”长城“，”真“，”伟大“】
     * 2.匹配
     * 匹配查询中要用到两个参数。 operator：默认为or。  minimumShouldMatch：默认为1。
     * 3.operator
     * 为or时：索引库中，只要文档的content这个字段内容包含“万里长城”，“里”，“真”，“伟大”等任何一个分词，该条文档就会被索引到。
     * 为and时：索引库中，文档的content这个字段必须包含“万里长城”，“里”，“真”，“伟大”等所有分词 ，这就是and。
     * 4.minimumShouldMatch
     * ①只有operator为or时，minimumShouldMatch才有效。
     * 毕竟operator为and时，要求全部都匹配上，都要满足，minimumShouldMatch这边你又设置了只要满足两个词条就可以返回，两个条件冲突了。
     * ②minimumShouldMatch这个api的主要目的是为了避免搜索不精确，比如万里长城真伟大。
     * 如果索引库中一个文档的内容中，包含“真”字就返回了，这就不科学了。
     * ③为0时：
     * 是不是猜测，为0的时候，一个都不用匹配到，就可以返回。
     * 然而结果是残酷的，我设置了为0，并不是全部返回，还是要有一个匹配的词条才会返回。
     * ES对这个api还是有所限制的，毕竟如果返回全部内容的话，ES检索没有意义。
     */
    @Test
    public void givenSavedDoc_whenDelete_thenRemovedFromIndex() {
        final String articleTitle = "Spring Data Elasticsearch";

        final Query searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchQuery("title", articleTitle)
                        .minimumShouldMatch("75%"))
                .build();
        final SearchHits<Article> articles = elasticsearchOperations
                .search(searchQuery, Article.class, IndexCoordinates.of("blog"));

        assertEquals(1, articles.getTotalHits());
        final long count = articleRepository.count();

        articleRepository.delete(articles.getSearchHit(0)
                .getContent());

        assertEquals(count - 1, articleRepository.count());
    }

    /**
     * 查询title中含有 Search engines 的数据
     */
    @Test
    public void givenSavedDoc_whenOneTermMatches_thenFindByTitle() {
        final Query searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchQuery("title", "Search engines").operator(AND))
                .build();
        final SearchHits<Article> articles = elasticsearchOperations
                .search(searchQuery, Article.class, IndexCoordinates.of("blog"));
        assertEquals(1, articles.getTotalHits());
    }
}