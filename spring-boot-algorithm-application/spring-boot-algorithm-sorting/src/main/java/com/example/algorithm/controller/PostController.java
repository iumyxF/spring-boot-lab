package com.example.algorithm.controller;

import com.example.algorithm.entities.Post;
import com.example.algorithm.entities.vo.PostVo;
import com.example.algorithm.service.IPostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author iumyxF
 * @description:
 * @date 2023/4/28 15:54
 */
@RestController
public class PostController {

    @Resource
    private IPostService postService;

    @GetMapping("/list")
    public List<Post> list() {
        return postService.listPost();
    }

    @GetMapping("/list/{limit}")
    public List<Post> listLimit(@PathVariable String limit) {
        List<Post> posts = postService.listPost();
        return posts.stream().limit(Long.parseLong(limit)).collect(Collectors.toList());
    }

    @GetMapping("/list/sort")
    public List<PostVo> listSort() {
        return postService.sortListPost();
    }

    @GetMapping("/list/sort/{limit}")
    public List<PostVo> listLimitSort(@PathVariable String limit) {
        List<PostVo> postVos = postService.sortListPost();
        return postVos.stream().limit(Long.parseLong(limit)).collect(Collectors.toList());
    }

}
