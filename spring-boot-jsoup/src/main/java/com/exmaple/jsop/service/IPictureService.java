package com.exmaple.jsop.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exmaple.jsop.domain.entities.Picture;

/**
 * @author fzy
 * @description:
 * @date 2023/5/31 9:15
 */
public interface IPictureService {
    /**
     * 分页查询图片资源
     *
     * @param searchText 关键词
     * @param current    当前页
     * @param size       当前页数量
     * @return 结果
     */
    Page<Picture> searchPicture(String searchText, long current, long size);
}
