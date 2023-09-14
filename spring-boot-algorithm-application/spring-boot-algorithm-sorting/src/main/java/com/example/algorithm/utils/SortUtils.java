package com.example.algorithm.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.example.algorithm.entities.Post;
import com.example.algorithm.entities.vo.PostVo;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;


/**
 * @author iumyxF
 * @description:
 * @date 2023/4/28 16:23
 */
public class SortUtils {

    /**
     * 算法来源
     * <a href="https://www.biaodianfu.com/product-ranking-algorithm.html">...</a>
     * 点赞数设为 p，发帖时间设为 t （小时计算）
     * 计算公式 (p-1)/[pow((t+2),G)]
     * 其中G控制更新频率 G的值越大，score的衰减速度越快，排行的更新越频繁。暂定1.2吧
     *
     * @param postList
     */
    public static List<PostVo> hotnessSortingAlgorithm(List<Post> postList) {
        ArrayList<PostVo> postVos = new ArrayList<>(postList.size());
        DateTime currentDate = DateUtil.date();
        //记录每一个post的权重
        for (Post post : postList) {
            //当前时间和创建时间的时间差（小时）
            long hourGap = DateUtil.between(post.getCreateTime(), currentDate.toJdkDate(), DateUnit.HOUR);
            BigDecimal weights = calculationOfWeights(post.getLikes(), post.getComments(), hourGap);
            PostVo postVo = BeanUtil.copyProperties(post, PostVo.class);
            postVo.setDataWeights(weights);
            postVos.add(postVo);
        }
        return postVos;
    }

    /**
     * 计算权重
     *
     * @param likes    点赞数
     * @param comments 评论数
     * @param hourGap  小时差
     * @return 权重
     */
    public static BigDecimal calculationOfWeights(long likes, long comments, long hourGap) {
        BigDecimal likesNum = new BigDecimal(likes);
        BigDecimal commentsNum = new BigDecimal(comments);
        BigDecimal hourGapNum = new BigDecimal(hourGap);
        BigDecimal factor = new BigDecimal("1.2");
        BigDecimal one = new BigDecimal("1");
        BigDecimal two = new BigDecimal("2");
        // 设置精度和舍入模式
        MathContext mc = new MathContext(10, RoundingMode.HALF_UP);
        // 计算表达式
        BigDecimal result = likesNum.subtract(one).divide(hourGapNum.add(two).pow(factor.intValue(), mc), mc);
        // 设置小数点后7位
        String formattedResult = result.setScale(7, RoundingMode.HALF_UP).toString();
        return BigDecimal.valueOf(Double.parseDouble(formattedResult));
    }

}
