package com.example.crawler.model.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author iumyxF
 * @description: 专辑信息
 * @date 2023/12/19 9:49
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Album {
    
    private Long id;
    private String name;
    private Artist artist;
    private Long publishTime;
    private Integer size;
    private Long copyrightId;
    private Integer status;
    private Long picId;
    private Integer mark;
}
