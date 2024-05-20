package com.example.translation;

import com.alibaba.excel.EasyExcel;
import com.example.translation.dao.WordRepository;
import com.example.translation.entities.LanguageConstant;
import com.example.translation.entities.Word;
import com.example.translation.listener.WordListener;
import com.example.translation.service.TranslationService;
import com.example.translation.service.impl.AliTranslationServiceImpl;
import com.google.common.collect.Lists;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author fzy
 * @description:
 * @date 2024/4/16 10:37
 */

public class TranslationApplication {

    /**
     * 使用步骤：
     * <p>
     * 1. 访问 <a href="https://help.aliyun.com/zh/sls/developer-reference/accesskey-pair?spm=a2c4g.11186623.0.i4"/>
     * 2. 按照步骤获取ak、sk
     * 3. 进入AliTranslationServiceImpl.class 填写ak、sk
     */
    public static void main(String[] args) {
        // 读取未翻译单词数据
        String fileName = "F:\\testData\\translate\\test.xlsx";
        List<Word> wordList = readByExcel(fileName);
        // 多线程翻译
        List<Word> resWords = doTranslate(wordList);
        // 写入新的excel
        String outputFileName = generateOutputFileName(fileName);
        writeExcel(outputFileName, resWords);
    }

    /**
     * 读取excel
     *
     * @param fileName 文件全路径
     * @return 单词数据
     */
    public static List<Word> readByExcel(String fileName) {
        WordRepository repository = WordRepository.getInstance();
        try (InputStream inputStream = Files.newInputStream(Paths.get(fileName))) {
            EasyExcel.read(inputStream, Word.class, new WordListener()).sheet().doRead();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return repository.getAll();
    }

    /**
     * 多线程进行翻译
     *
     * @param wordList 带翻译单词列表
     * @return 已翻译单词列表
     */
    private static List<Word> doTranslate(List<Word> wordList) {
        // 保存临时结果
        ConcurrentLinkedQueue<Word> resWords = new ConcurrentLinkedQueue<>();
        // 创建翻译类,如果使用Spring直接注入即可
        TranslationService service = new AliTranslationServiceImpl();
        // 按照50个单词为一组进行翻译
        List<List<Word>> partition = Lists.partition(wordList, 50);
        ExecutorService threadPool = Executors.newFixedThreadPool(partition.size());
        List<CompletableFuture<Void>> futures = new ArrayList<>(partition.size());

        for (List<Word> requests : partition) {
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                List<Word> response = service.getBatchTranslate(requests, LanguageConstant.CHINESE, LanguageConstant.ENGLISH);
                resWords.addAll(response);
            }, threadPool);
            futures.add(future);
        }
        //同步阻塞等待结果
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        //因为使用main函数执行,需要手动关闭线程池才能正常结束,如果使用web容器则不需要关闭
        threadPool.shutdown();
        try {
            threadPool.awaitTermination(120, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return new ArrayList<>(resWords);
    }

    /**
     * 写入excel
     *
     * @param outputFileName 输出文件全路径
     * @param resWords       翻译结果
     */
    private static void writeExcel(String outputFileName, List<Word> resWords) {
        EasyExcel.write(outputFileName, Word.class)
                .sheet(LanguageConstant.ENGLISH)
                .doWrite(resWords);
    }

    /**
     * 生成翻译文件名
     *
     * @param inputFileName 读取的文件名
     * @return 结果
     */
    private static String generateOutputFileName(String inputFileName) {
        String baseName = Paths.get(inputFileName).getFileName().toString();
        String outputFileName = baseName.replace(".xlsx", "") + "_translated.xlsx";
        return "F:\\testData\\translate\\" + outputFileName;
    }
}
