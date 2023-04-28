package com.example.algorithm.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.example.algorithm.entities.Post;
import com.example.algorithm.entities.vo.PostVo;
import com.example.algorithm.service.IPostService;
import com.example.algorithm.utils.NameUtils;
import com.example.algorithm.utils.SortUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author fzy
 * @description:
 * @date 2023/4/28 15:57
 */
@Service
public class PostServiceImpl implements IPostService {

    private List<Post> postList;

    private List<PostVo> postVoList;

    private static final Integer LEN = 1024;
    @PostConstruct
    public void init() {
        postList = new ArrayList<>(1024);
        for (int i = 0; i < LEN; i++) {
            Post post = new Post();
            post.setId((long) i);
            post.setCreatBy(NameUtils.createChineseName());
            post.setName(RandomUtil.randomNumbers(5));
            post.setLikes(RandomUtil.randomLong(0, 1000));
            post.setComments(RandomUtil.randomLong(0, 1000));
            post.setCreateTime(RandomUtil.randomDay(-180, 0));
            postList.add(post);
        }
        List<PostVo> postVos = SortUtils.hotnessSortingAlgorithm(postList);
        postVoList = postVos.stream().sorted(Comparator.comparingDouble(postVo -> -postVo.getDataWeights().doubleValue())).collect(Collectors.toList());
    }

    @Override
    public List<Post> listPost() {
        return postList;
    }

    @Override
    public List<PostVo> sortListPost() {
        return postVoList;
    }
}
