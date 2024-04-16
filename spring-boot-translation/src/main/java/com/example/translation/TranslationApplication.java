package com.example.translation;

import cn.hutool.core.date.DateUtil;
import com.alibaba.excel.EasyExcel;
import com.example.translation.dao.WordRepository;
import com.example.translation.entities.LanguageConstant;
import com.example.translation.entities.Word;
import com.example.translation.listener.WordListener;
import com.example.translation.service.TranslationService;
import com.example.translation.service.impl.AliTranslationServiceImpl;
import com.google.common.collect.Lists;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author fzy
 * @description:
 * @date 2024/4/16 10:37
 */

public class TranslationApplication {

    public static void main(String[] args) throws IOException, InterruptedException {
        //读取
        String fileName = "F:\\testData\\translate\\test.xlsx";
        EasyExcel.read(Files.newInputStream(Paths.get(fileName)), Word.class, new WordListener()).sheet().doRead();

        //获取
        WordRepository repository = WordRepository.getInstance();
        List<Word> wordList = repository.getAll();
        int size = wordList.size();
        System.out.println(size);

        //多线程翻译
        ArrayList<Word> resWords = new ArrayList<>();
        List<List<Word>> partition = Lists.partition(wordList, 50);
        TranslationService service = new AliTranslationServiceImpl();
        ExecutorService threadPool = Executors.newFixedThreadPool(partition.size());
        List<CompletableFuture<Boolean>> futures = new ArrayList<>(partition.size());
        for (List<Word> requests : partition) {
            CompletableFuture<Boolean> future = CompletableFuture.supplyAsync(() -> {
                List<Word> response = service.getBatchTranslate(requests, LanguageConstant.CHINESE, LanguageConstant.ENGLISH);
                resWords.addAll(response);
                return true;
            }, threadPool);
            futures.add(future);
        }
        futures.forEach(CompletableFuture::join);
        //写入
        String fileName2 = "F:\\testData\\translate\\" + DateUtil.format(new Date(), "yyyyMMddHHmmss") + ".xlsx";
        EasyExcel.write(fileName2, Word.class)
                .sheet(LanguageConstant.ENGLISH)
                .doWrite(resWords);

        // 关闭线程池
        threadPool.shutdown();
        threadPool.awaitTermination(120, TimeUnit.NANOSECONDS);
    }

}
