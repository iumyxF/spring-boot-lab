package com.example.algorithm.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author fzy
 * @description: 帖子对象
 * @date 2023/4/28 15:50
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    /**
     * id
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 作者
     */
    private String creatBy;

    /**
     * 点赞数
     */
    private Long likes;

    /**
     * 评论数
     */
    private Long comments;

    /**
     * 发布时间
     */
    private Date createTime;
}
