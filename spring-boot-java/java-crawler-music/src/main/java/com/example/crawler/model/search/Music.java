package com.example.crawler.model.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author iumyxF
 * @description:
 * @date 2023/12/19 9:47
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Music {

    private Long id;
    private String name;
    private List<Artist> artists;
    private Album album;
    private Long duration;
    private Long copyrightId;
    private Integer status;
    private List<String> alias;
    private Integer rtype;
    private Integer ftype;
    private Long mvid;
    private Integer fee;
    private String rUrl;
    private Long mark;
}
