package com.exmaple.jsop.domain.entities;

import lombok.Data;

import java.io.Serializable;

/**
 * @author fzy
 * @description: 图片实体
 * @date 2023/5/31 9:06
 */
@Data
public class Picture implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 图片标题
     */
    private String title;

    /**
     * 图片网络地址
     */
    private String url;
}
