package com.example.crawler.model.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author iumyxF
 * @description: 作家
 * @date 2023/12/19 9:50
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Artist {

    private Integer id;
    private String name;
    private String picUrl;
    private List<String> alias;
    private Integer albumSize;
    private Integer picId;
    private String fansGroup;
    private String img1v1Url;
    private Integer img1v1;
    private String trans;
}