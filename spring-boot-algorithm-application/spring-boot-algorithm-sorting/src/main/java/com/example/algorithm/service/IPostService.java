package com.example.algorithm.service;

import com.example.algorithm.entities.Post;
import com.example.algorithm.entities.vo.PostVo;

import java.util.List;

/**
 * The interface Post service.
 *
 * @author iumyxF
 * @description:
 * @date 2023 /4/28 15:56
 */
public interface IPostService {

    /**
     * List post list.
     *
     * @return the list
     */
    List<Post> listPost();

    /**
     * Sort list post list.
     *
     * @return the list
     */
    List<PostVo> sortListPost();
}
