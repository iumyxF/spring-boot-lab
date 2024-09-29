package com.example.elastic.exmaple;

import cn.hutool.core.collection.CollUtil;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.*;
import co.elastic.clients.elasticsearch._types.mapping.Property;
import co.elastic.clients.elasticsearch._types.mapping.TypeMapping;
import co.elastic.clients.elasticsearch._types.query_dsl.FunctionBoostMode;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.core.search.HitsMetadata;
import co.elastic.clients.elasticsearch.indices.CreateIndexRequest;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import co.elastic.clients.elasticsearch.indices.DeleteIndexRequest;
import co.elastic.clients.elasticsearch.indices.DeleteIndexResponse;
import co.elastic.clients.json.JsonData;
import co.elastic.clients.transport.endpoints.BooleanResponse;
import com.example.elastic.model.Hotel;
import com.example.elastic.model.HotelDoc;
import com.example.elastic.service.HotelService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author fzy
 * @description: es的案例测试
 * @date 2024/9/23 10:57
 */

@SpringBootTest
class EsExampleTest {

    @Autowired
    ElasticsearchClient client;
    @Autowired
    HotelService hotelService;

    //region 索引操作

    /**
     * 创建一个Hotel的索引库
     * 字段属性分析看{@link Hotel}
     * 得到 index 创建 JSON = "{"mappings":{"properties":{"id":{"type":"keyword"},"name":{"type":"text","analyzer":"smartcn"},"address":{"type":"keyword"},"price":{"type":"integer"},"score":{"type":"integer"},"brand":{"type":"keyword"},"city":{"type":"keyword"},"starName":{"type":"keyword"},"business":{"type":"keyword"},"latitude":{"type":"geo_point"},"longitude":{"type":"geo_point"},"pic":{"type":"keyword","index":false}}}}"
     * <p>
     * 由于name、brand、city字段都需要参与搜索，也就意味着用户在搜索的时候，会根据多个字段搜索，要使用copy_to属性将当前字段拷贝到某个字段实现
     * 如：{"mappings":{"properties":{"search_word":{"type":"text","analyzer":"smartcn"},"brand":{"type":"keyword","copy_to":"search_word"},"name":{"type":"keyword","copy_to":"search_word"}}}}
     * 将brand和name都复制到search_word字段，这样能实现多个字段的查询
     * 修改后 index 创建 JSON = "{"mappings":{"properties":{"id":{"type":"keyword"},"name":{"type":"text","analyzer":"smartcn","copy_to":"search_word"},"address":{"type":"keyword"},"price":{"type":"integer"},"score":{"type":"integer"},"brand":{"type":"keyword","copy_to":"search_word"},"city":{"type":"keyword"},"starName":{"type":"keyword"},"business":{"type":"keyword","copy_to":"search_word"},"latitude":{"type":"geo_point"},"longitude":{"type":"geo_point"},"pic":{"type":"keyword","index":false},"search_word":{"type":"text","analyzer":"smartcn"}}}}"
     */
    @Test
    public void createIndex() throws IOException {
        List<String> searchWord = Collections.singletonList("search_word");
        CreateIndexResponse response = client.indices()
                .create(new CreateIndexRequest.Builder()
                        .index("hotel")
                        .mappings(new TypeMapping.Builder()
                                .properties("id", Property.of(pBuilder -> pBuilder.keyword(kBuilder -> kBuilder.index(true))))
                                .properties("name", Property.of(pBuilder -> pBuilder.text(kBuilder -> kBuilder.index(true)
                                        .analyzer("smartcn")
                                        .copyTo(searchWord))))
                                .properties("address", Property.of(pBuilder -> pBuilder.keyword(kBuilder -> kBuilder.index(true))))
                                .properties("price", Property.of(pBuilder -> pBuilder.integer(kBuilder -> kBuilder.index(true))))
                                .properties("score", Property.of(pBuilder -> pBuilder.integer(kBuilder -> kBuilder.index(true))))
                                .properties("brand", Property.of(pBuilder -> pBuilder.keyword(kBuilder -> kBuilder.index(true)
                                        .copyTo(searchWord))))
                                .properties("city", Property.of(pBuilder -> pBuilder.keyword(kBuilder -> kBuilder.index(true))))
                                .properties("starName", Property.of(pBuilder -> pBuilder.keyword(kBuilder -> kBuilder.index(true))))
                                .properties("business", Property.of(pBuilder -> pBuilder.keyword(kBuilder -> kBuilder.index(true)
                                        .copyTo(searchWord))))
                                .properties("location", Property.of(pBuilder -> pBuilder.geoPoint(kBuilder -> kBuilder.index(true))))
                                .properties("pic", Property.of(pBuilder -> pBuilder.keyword(kBuilder -> kBuilder.index(false))))
                                .properties("search_word", Property.of(pBuilder -> pBuilder.text(kBuilder -> kBuilder.index(true)
                                        .analyzer("smartcn"))))
                                .build())
                        .build());
        System.out.println(response.toString());
    }

    @Test
    public void exitIndex() throws IOException {
        BooleanResponse response = client.indices()
                .exists(builder -> builder.index("hotel"));
        System.out.println(response.value());
    }

    @Test
    public void deleteIndex() throws IOException {
        DeleteIndexResponse response = client.indices()
                .delete(new DeleteIndexRequest.Builder()
                        .index(Collections.singletonList("hotel"))
                        .build());
        System.out.println(response);
    }

    //endregion

    //region 文档

    @Test
    public void getDocById() throws IOException {
        GetResponse<HotelDoc> response = client.get(builder -> builder
                .index("hotel")
                .id(String.valueOf(1)), HotelDoc.class);
        // 不存在的情况：GetResponse: {"_index":"hotel","found":false,"_id":"1","_type":"_doc"}
        // 注意构造方法
        System.out.println(response);
    }

    /**
     * match_all: 查询全部文档
     */
    @Test
    public void queryMatchAll() throws IOException {
        SearchResponse<HotelDoc> searchResponse = client.search(s -> s
                        .index("hotel")
                        .query(q -> q.matchAll(v -> v))
                , HotelDoc.class);
        printResult(searchResponse);
    }

    /**
     * match: 分词模糊查询，针对text类型，类似mysql的 like查询
     * {"query":{"match":{"search_word":"文"}}}
     */
    @Test
    public void queryMatch() throws IOException {
        SearchResponse<HotelDoc> searchResponse = client.search(s -> s
                        .index("hotel")
                        .query(q -> q
                                .match(m -> m
                                        //.field("name")
                                        .field("search_word")
                                        .query("公司")))
                , HotelDoc.class);
        printResult(searchResponse);
    }

    /**
     * 多条件查询
     * {"query":{"multi_match":{"query":"文","fields":["name","brand","business"]}}}
     * 注意：因为字段上面没有进行分词，所以可能出现的结果和search_word不同
     */
    @Test
    public void queryMultiMatch() throws IOException {
        SearchResponse<HotelDoc> searchResponse = client.search(s -> s
                        .index("hotel")
                        .query(q -> q
                                .multiMatch(m -> m
                                        .query("文")
                                        .fields("name", "brand", "business")
                                ))
                , HotelDoc.class);
        printResult(searchResponse);
    }

    /**
     * 精确查询 term,针对keyword类型，类似mysql的 brand = 'xxx'
     * 就算查询text分词，也不会模糊匹配
     */
    @Test
    public void queryTerm() throws IOException {
        SearchResponse<HotelDoc> searchResponse = client.search(s -> s
                        .index("hotel")
                        .query(q -> q
                                .term(t -> t
                                        // brand - keyword
                                        //.field("brand")
                                        // name -text
                                        .field("name")
                                        .value("广东省")))
                , HotelDoc.class);
        printResult(searchResponse);
    }

    @Test
    public void queryRange() throws IOException {
        SearchResponse<HotelDoc> searchResponse = client.search(s -> s
                        .index("hotel")
                        .query(q -> q
                                .range(r -> r.field("price")
                                        .lte(JsonData.of(1296016577))
                                        .gte(JsonData.of(1206915764))
                                ))
                , HotelDoc.class);
        printResult(searchResponse);
    }

    /**
     * 复合查询
     * 1. function score：算分函数查询，可以控制文档相关性算分，控制文档排名(例如搜索引擎的排名，第一大部分都是广告)
     * 根据相关度打分是比较合理的需求，但是合理的并不一定是产品经理需要的
     * 以某搜索引擎为例，你在搜索的结果中，并不是相关度越高就越靠前，而是谁掏的钱多就让谁的排名越靠前
     * 要想控制相关性算分，就需要利用ES中的function score查询了
     * <p>
     * 2. bool query：布尔查询，利用逻辑关系组合多个其他的查询，实现复杂搜索
     */
    @Test
    public void queryFunctionScore() throws IOException {
        SearchResponse<HotelDoc> response = client.search(s -> s
                        .query(q -> q
                                .functionScore(fs -> fs
                                        // 条件查询 查询出"工程"词语的文档
                                        .query(fsq -> fsq
                                                .match(m -> m
                                                        .field("search_word")
                                                        .query("工程")
                                                )
                                        )
                                        // 算分函数，结果成为functions score 后面会与上面原市的score进行运算，得到最终分数
                                        .functions(fus -> fus
                                                // 得到id=4的文档
                                                .filter(filter -> filter
                                                        .term(t -> t
                                                                .field("id")
                                                                .value(4)
                                                        )
                                                )
                                                //算分函数有:
                                                // 1. weight: 指定常量值作为结果
                                                // 2. field_value_factor: 用文档中的某个字段作为函数结果
                                                // 3. random_score:随机生成一个值
                                                // 4. script_score:自定义计算公式，公式结果作为函数结果
                                                .weight(10.0)
                                        )
                                        // 加权模式，指定分算函数结果和原始score的运算模式
                                        // Multiply 两个结果相乘
                                        // replace 将分算函数结果替换原来的score
                                        // 其他sum、avg、max、min
                                        .boostMode(FunctionBoostMode.Multiply)
                                )
                        )
                , HotelDoc.class);
        printResult(response);
    }

    /**
     * 布尔查询是一个或多个子查询的组合，每一个子句就是一个子查询。子查询的组合方式有
     * must：必须匹配每个子查询，类似 '与'
     * should：选择性匹配子查询，类似 '或'
     * must_not：必须不匹配，不参与算分，类似 '非'
     * filter：必须匹配，不参与算分
     */
    @Test
    public void queryBool() throws IOException {
        // 搜索search_word中包含'股份有限公司'，price不高于1226428922，在坐标36.30556423523153, 104.48060937499996周围10km范围内的酒店
        SearchResponse<HotelDoc> response = client.search(s -> s
                        .index("hotel")
                        .query(q -> q
                                .bool(b -> b
                                        .must(m -> m
                                                .match(match -> match
                                                        .query("股份有限公司")
                                                        .field("search_word")
                                                )
                                        )
                                        .mustNot(mn -> mn
                                                .range(r -> r
                                                        .field("price")
                                                        .gte(JsonData.of("1226428922"))
                                                )
                                        )
                                        .filter(f -> f
                                                .geoDistance(geo -> geo
                                                        .distance("1700km")
                                                        .distanceType(GeoDistanceType.Arc)
                                                        .field("location")
                                                        .location(l -> l
                                                                .latlon(ll -> ll
                                                                        .lat(36.30556423523153)
                                                                        .lon(104.48060937499996)
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                , HotelDoc.class);
        printResult(response);
    }

    /**
     * must和filter，效果是一样的，条件筛选，
     * 如果结果需要算分，则使用must，否则使用filter（效率快）
     * 个人理解：例如，如果查城市名，我们搜索相关度是要计算分数的，所以用must，筛选价格不需要计分，则用filter
     */
    @Test
    public void queryBool2() throws IOException {
        // 搜索城市在aa，品牌为xx或者yy，价格不低于bb，且用户评分在cc分以上的酒店
        SearchResponse<HotelDoc> response = client.search(s -> s
                        .index("hotel")
                        .query(q -> q
                                .bool(b -> b
                                        .must(m -> m
                                                .match(t -> t
                                                        .field("search_word")
                                                        .query("公司")
                                                )
                                        )
                                        //should 和 must同一级 不生效
                                        //.should(sh -> sh
                                        //        .term(t -> t
                                        //                .field("brand")
                                        //                .value("唤韶发展有限责任公司")
                                        //        )
                                        //)
                                        //.should(sh -> sh
                                        //        .term(t -> t
                                        //                .field("brand")
                                        //                .value("庐畦置业有限责任公司")
                                        //        )
                                        //)
                                        // 解决方案：用一个must嵌套
                                        .must(m -> m
                                                .bool(bo -> bo
                                                        .should(sh -> sh
                                                                .term(t -> t
                                                                        .field("brand")
                                                                        .value("唤韶发展有限责任公司")
                                                                )
                                                        )
                                                        .should(sh -> sh
                                                                .term(t -> t
                                                                        .field("brand")
                                                                        .value("庐畦置业有限责任公司")
                                                                )
                                                        )
                                                )
                                        )
                                        .filter(f -> f
                                                .range(r -> r
                                                        .field("price")
                                                        .gte(JsonData.of("1"))
                                                )
                                        )
                                        .filter(f -> f
                                                .range(r -> r
                                                        .field("score")
                                                        .gte(JsonData.of("1"))
                                                )
                                        )
                                )
                        )
                , HotelDoc.class);
        printResult(response);
    }

    /**
     * 排序
     * order by xxx [desc/asc]
     */
    @Test
    public void querySort() throws IOException {
        // 多字段
        ArrayList<SortOptions> sortOptions = new ArrayList<>();
        sortOptions.add(SortOptions.of(s -> s.field(f -> f.field("price").order(SortOrder.Desc))));
        sortOptions.add(SortOptions.of(s -> s.field(f -> f.field("score").order(SortOrder.Asc))));
        SearchResponse<HotelDoc> searchResponse = client.search(s -> s
                        .index("hotel")
                        .query(q -> q
                                .matchAll(m -> m)
                        )
                        // 单字段
                        //.sort(sort -> sort
                        //        .field(f -> f
                        //                .field("price")
                        //                .order(SortOrder.Desc)
                        //        )
                        //)
                        .sort(sortOptions)
                , HotelDoc.class);
        printResult(searchResponse);
    }

    /**
     * 分页查询
     * 如果分页数量过大，会造成es解点故障
     * 解决方案：<a href="https://www.elastic.co/guide/en/elasticsearch/reference/7.17/paginate-search-results.html"/>
     */
    @Test
    public void queryPage() throws IOException {
        SearchResponse<HotelDoc> searchResponse = client.search(s -> s
                        .index("hotel")
                        .query(q -> q
                                .matchAll(m -> m)
                        )
                        .from(9991)
                        .size(10)
                        .sort(sort -> sort
                                .field(f -> f
                                        .field("price")
                                        .order(SortOrder.Asc)
                                )
                        )
                , HotelDoc.class);
        printResult(searchResponse);
    }

    /**
     * 深度分页查询
     * search_after
     * 使用 search_after 需要具有相同 query 和 sort 值的多个搜索请求。
     * 如果在这些请求之间发生刷新，则结果的顺序可能会更改，从而导致页面之间的结果不一致。
     * 为防止这种情况，可以创建一个时间点 （PIT） 来保留搜索的当前索引状态。
     */
    @Test
    public void queryDepthPage() throws IOException {
        // 正常查询
        //SearchResponse<HotelDoc> search1 = client.search(s -> s.index("hotel")
        //                .query(query -> query.matchAll(ma -> ma))
        //                .size(2)
        //                .sort(sort -> sort.field(f -> f.field("address").order(SortOrder.Desc)))
        //        , HotelDoc.class);
        //printResult(search1);

        // 使用pid查询
        OpenPointInTimeResponse openPointInTimeResponse = client.openPointInTime(p -> p
                .index("hotel")
                .keepAlive(v -> v
                        .time("1m")
                )
        );
        String id = openPointInTimeResponse.id();
        SearchResponse<HotelDoc> searchResponse = client.search(s -> s
                        .size(2)
                        .pit(p -> p.keepAlive(k -> k.time("1m")).id(id))
                        .sort(sort -> sort.field(f -> f.field("address").order(SortOrder.Desc)))
                , HotelDoc.class);
        printResult(searchResponse);

        // 获取上一页最后一个排序信息
        List<Hit<HotelDoc>> hits = searchResponse.hits().hits();
        List<FieldValue> values = hits.get(hits.size() - 1).sort();

        // 查询下一页
        System.out.println("下一页信息：");
        SearchResponse<HotelDoc> searchResponse2 = client.search(s -> s
                        .size(2)
                        // 延续时间1min
                        .pit(p -> p.keepAlive(k -> k.time("1m")).id(id))
                        .sort(sort -> sort.field(f -> f.field("address").order(SortOrder.Desc)))
                        .searchAfter(values)
                        .trackTotalHits(t -> t.enabled(false))
                , HotelDoc.class);
        printResult(searchResponse2);

        // 最后使用完毕可以手动关闭
        client.closePointInTime(c -> c.id(id));
    }

    @Test
    public void queryHighlight() throws IOException {
        SearchResponse<HotelDoc> search1 = client.search(s -> s.index("hotel")
                        .query(query -> query.match(ma -> ma.field("name").query("文")))
                        .highlight(h -> h
                                .fields("name", hf -> hf
                                        .requireFieldMatch(false)))
                , HotelDoc.class);

        //search1.hits().hits().get(0).highlight();
        printResult(search1);
    }

    /**
     * GEO
     * 坐标矩阵查询，根据左上角和右下角筛选一个矩形范围
     */
    @Test
    public void queryGeoByBox() throws IOException {
        SearchResponse<HotelDoc> searchResponse = client.search(s -> s
                        .index("hotel")
                        .query(q -> q
                                .geoBoundingBox(g -> g
                                        .field("location")
                                        .boundingBox(b -> b
                                                .tlbr(t -> t
                                                        // 经度:109.5702918030936 纬度:42.81018784349334
                                                        .topLeft(tl -> tl
                                                                .latlon(lat -> lat
                                                                        .lon(109.5702918030936)
                                                                        .lat(42.81018784349334)
                                                                )
                                                        )
                                                        // 经度:118.14915253959975 纬度:21.739743055059776
                                                        .bottomRight(br -> br
                                                                .latlon(lat -> lat
                                                                        .lon(118.14915253959975)
                                                                        .lat(21.739743055059776)
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                , HotelDoc.class);
        printResult(searchResponse);
    }

    /**
     * GEO
     * 圆心查询 查询某个坐标附近 x km范围的信息
     */
    @Test
    public void queryGeoByCircle() throws IOException {
        SearchResponse<HotelDoc> response = client.search(s -> s
                        .index("hotel")
                        .query(q -> q
                                .geoDistance(g -> g
                                        .distance("1700km")
                                        .field("location")
                                        .location(l -> l
                                                .latlon(v -> v
                                                        .lat(36.30556423523153)
                                                        .lon(104.48060937499996)
                                                ))
                                        .distanceType(GeoDistanceType.Arc)
                                )
                        )
                // 写法2:
                //.bool(b -> b
                //        .filter(f -> f
                //                .geoDistance(g -> g
                //                        .distance("1700km")
                //                        .field("location")
                //                        .location(l -> l
                //                                .latlon(v -> v
                //                                        .lat(36.30556423523153)
                //                                        .lon(104.48060937499996)
                //                                ))
                //                        .distanceType(GeoDistanceType.Arc)
                //                ))
                //))
                , HotelDoc.class);
        printResult(response);
    }

    @Test
    public void insertDoc() throws IOException {
        Hotel hotel = hotelService.getById(1L);
        CreateResponse response = client.create(builder -> builder
                .index("hotel")
                .id(String.valueOf(hotel.getId()))
                .document(new HotelDoc(hotel)));
        System.out.println(response);
    }

    /**
     * 如果某个id存在文档 会直接更新
     */
    @Test
    public void batchInsertDoc() throws IOException {
        List<Hotel> hotels = hotelService.list();
        BulkRequest.Builder br = new BulkRequest.Builder();
        for (Hotel hotel : hotels) {
            br.operations(op -> op
                    .index(idx -> idx
                            .index("hotel")
                            .id(String.valueOf(hotel.getId()))
                            .document(new HotelDoc(hotel))
                    )
            );
        }
        BulkResponse response = client.bulk(br.build());
        System.out.println(response);
    }

    @Test
    public void delDocById() throws IOException {
        DeleteResponse response = client.delete(builder -> builder
                .index("hotel")
                .id(String.valueOf(1)));
        System.out.println(response);
    }

    @Test
    public void delAllDoc() throws IOException {
        DeleteByQueryResponse deleteByQueryResponse = client.deleteByQuery(d -> d
                .index("hotel")
                .query(q -> q.matchAll(v -> v)));
        System.out.println(deleteByQueryResponse);
    }

    /**
     * 全量和增量更新的API是一样的，区别是doc内的对象赋值是否完全
     */
    @Test
    public void docUpdateById() throws IOException {
        Hotel hotel = new Hotel();
        hotel.setLatitude("24.744450841392773");
        hotel.setLongitude("83.23270706538476");
        UpdateResponse<HotelDoc> updateResponse = client.update(builder -> builder.index("hotel")
                        .id(String.valueOf(1))
                        .doc(new HotelDoc(hotel))
                , HotelDoc.class);
        System.out.println(updateResponse);
    }

    //endregion

    private void printResult(SearchResponse<HotelDoc> searchResponse) {
        List<Hit<HotelDoc>> hitList = Optional.of(searchResponse)
                .map(SearchResponse::hits)
                .map(HitsMetadata::hits)
                .orElse(null);
        if (CollUtil.isNotEmpty(hitList)) {
            hitList.forEach(h -> System.out.println("score = " + h.score() + " , data = " + h.source()));
        }
    }
}
