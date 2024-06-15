package com.example.translation.listener;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.fastjson2.JSON;
import com.example.translation.dao.WordRepository;
import com.example.translation.entities.Word;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author iumyx
 * @description:
 * @date 2024/4/16 11:03
 */
public class WordListener implements ReadListener<Word> {

    private static final Logger logger = LoggerFactory.getLogger(WordListener.class);

    private static final Snowflake snowflake = IdUtil.getSnowflake(1, 1);

    /**
     * 每隔5条存储数据库，实际使用中可以100条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 100;
    /**
     * 缓存的数据
     */
    private List<Word> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    private final WordRepository repository;

    public WordListener() {
        repository = WordRepository.getInstance();
    }

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data    one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(Word data, AnalysisContext context) {
        logger.info("解析到一条数据:{}", JSON.toJSONString(data));
        // 设置index 和清空翻译词语
        data.setIndex(snowflake.nextIdStr());
        data.setTranslated(null);
        cachedDataList.add(data);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (cachedDataList.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        logger.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        logger.info("{}条数据，开始存储数据库！", cachedDataList.size());
        repository.save(cachedDataList);
        logger.info("存储数据库成功！");
    }
}
