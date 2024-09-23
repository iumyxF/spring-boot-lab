package com.example.elastic.exmaple;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.mapping.Property;
import co.elastic.clients.elasticsearch._types.mapping.TypeMapping;
import co.elastic.clients.elasticsearch.core.CreateResponse;
import co.elastic.clients.elasticsearch.core.DeleteResponse;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.core.UpdateResponse;
import co.elastic.clients.elasticsearch.indices.CreateIndexRequest;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import co.elastic.clients.elasticsearch.indices.DeleteIndexRequest;
import co.elastic.clients.elasticsearch.indices.DeleteIndexResponse;
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
    private HotelService hotelService;

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
                                .properties("name", Property.of(pBuilder -> pBuilder.text(kBuilder -> kBuilder.index(true).analyzer("smartcn").copyTo(searchWord))))
                                .properties("address", Property.of(pBuilder -> pBuilder.keyword(kBuilder -> kBuilder.index(true))))
                                .properties("price", Property.of(pBuilder -> pBuilder.integer(kBuilder -> kBuilder.index(true))))
                                .properties("score", Property.of(pBuilder -> pBuilder.integer(kBuilder -> kBuilder.index(true))))
                                .properties("brand", Property.of(pBuilder -> pBuilder.keyword(kBuilder -> kBuilder.index(true).copyTo(searchWord))))
                                .properties("city", Property.of(pBuilder -> pBuilder.keyword(kBuilder -> kBuilder.index(true))))
                                .properties("starName", Property.of(pBuilder -> pBuilder.keyword(kBuilder -> kBuilder.index(true))))
                                .properties("business", Property.of(pBuilder -> pBuilder.keyword(kBuilder -> kBuilder.index(true).copyTo(searchWord))))
                                .properties("location", Property.of(pBuilder -> pBuilder.geoPoint(kBuilder -> kBuilder.index(true))))
                                .properties("pic", Property.of(pBuilder -> pBuilder.keyword(kBuilder -> kBuilder.index(false))))
                                .properties("search_word", Property.of(pBuilder -> pBuilder.text(kBuilder -> kBuilder.index(true).analyzer("smartcn"))))
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

    @Test
    public void insertDoc() throws IOException {
        Hotel hotel = hotelService.getById(1L);
        CreateResponse response = client.create(builder -> builder
                .index("hotel")
                .id(String.valueOf(hotel.getId()))
                .document(new HotelDoc(hotel)));
        System.out.println(response);
    }

    @Test
    public void delDocById() throws IOException {
        DeleteResponse response = client.delete(builder -> builder
                .index("hotel")
                .id(String.valueOf(1)));
        System.out.println(response);
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
}
