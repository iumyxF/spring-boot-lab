package com.exmaple.jsop.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exmaple.jsop.domain.entities.Picture;
import com.exmaple.jsop.service.IPictureService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The type Picture service.
 *
 * @author iumyxF
 * @description:
 * @date 2023 /5/31 9:17
 */
@Service
public class PictureServiceImpl implements IPictureService {

    @Resource
    private ObjectMapper objectMapper;

    /**
     * 使用jsoup爬虫bing的图片
     *
     * @param searchText 关键词
     * @param pageNum    当前页
     * @param pageSize   当前页数量
     * @return
     */
    @Override
    public Page<Picture> searchPicture(String searchText, long pageNum, long pageSize) {
        long current = (pageNum - 1) * pageNum;
        //拼接网页地址，这里first有些重复...
        String url = String.format("https://cn.bing.com/images/search?q=%s&first=%s", searchText, current);
        Document doc;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new RuntimeException("数据获取异常");
        }
        Elements elements = doc.select(".iuscp.isv");
        List<Picture> pictures = new ArrayList<>();
        for (Element element : elements) {
            // 获取图片的属性转成map
            String m = element.select(".iusc").get(0).attr("m");
            Map map;
            try {
                map = objectMapper.readValue(m, Map.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("json解析异常");
            }
            //获取地址
            String murl = (String) map.get("murl");
            //获取标题
            String title = (String) map.get("t");
            Picture picture = new Picture();
            picture.setTitle(title);
            picture.setUrl(murl);
            pictures.add(picture);
            if (pictures.size() >= pageSize) {
                break;
            }
        }
        Page<Picture> picturePage = new Page<>(pageNum, pageSize);
        picturePage.setRecords(pictures);
        return picturePage;
    }
}
