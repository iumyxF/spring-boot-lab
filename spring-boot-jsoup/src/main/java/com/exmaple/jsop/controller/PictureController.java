package com.exmaple.jsop.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exmaple.jsop.domain.dto.PictureQueryRequest;
import com.exmaple.jsop.domain.entities.Picture;
import com.exmaple.jsop.domain.Result;
import com.exmaple.jsop.service.IPictureService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author iumyxF
 * @description:
 * @date 2023/5/31 9:02
 */
@RestController
public class PictureController {

    @Resource
    private IPictureService pictureService;

    @PostMapping("/list/page/pic")
    public Result<Page<Picture>> listPictureByPage(@RequestBody PictureQueryRequest queryRequest,
                                                   HttpServletRequest request) {
        long current = queryRequest.getCurrent();
        long size = queryRequest.getPageSize();
        String searchText = queryRequest.getSearchText();
        Page<Picture> picturePage = pictureService.searchPicture(searchText, current, size);
        return Result.ok(picturePage);

    }

}
