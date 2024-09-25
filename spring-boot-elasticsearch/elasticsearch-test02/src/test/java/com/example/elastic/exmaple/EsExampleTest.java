package com.example.elastic.exmaple;

import cn.hutool.core.collection.CollUtil;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.GeoDistanceType;
import co.elastic.clients.elasticsearch._types.mapping.Property;
import co.elastic.clients.elasticsearch._types.mapping.TypeMapping;
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
                                        .query("文")))
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
                                                                ))
                                                        // 经度:118.14915253959975 纬度:21.739743055059776
                                                        .bottomRight(br -> br
                                                                .latlon(lat -> lat
                                                                        .lon(118.14915253959975)
                                                                        .lat(21.739743055059776)
                                                                ))
                                                )
                                        )
                                )
                        )
                , HotelDoc.class);
        printResult(searchResponse);
    }

    /**
     * 圆心查询 查询某个坐标附近 x km范围的信息
     */
    @Test
    public void queryGeoByCircle() throws IOException {
        SearchResponse<HotelDoc> response = client.search(s -> s
                        .index("hotel")
                        .query(q -> q
                                .bool(b -> b
                                        .filter(f -> f
                                                .geoDistance(g -> g
                                                        .distance("1700km")
                                                        .field("location")
                                                        .location(l -> l
                                                                .latlon(v -> v
                                                                        .lat(36.30556423523153)
                                                                        .lon(104.48060937499996)
                                                                ))
                                                        .distanceType(GeoDistanceType.Arc)
                                                ))
                                ))
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
            hitList.forEach(h -> System.out.println(h.source()));
        }
    }
}
