package com.example.excel.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.excel.domain.User;
import com.example.excel.domain.WriteBo;
import com.example.excel.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author fzy
 * @description:
 * @date 2024/9/7 10:43
 */
@Slf4j
@Controller
@RequestMapping
public class ExportTestController {

    @Resource(name = "threadPoolTaskExecutor")
    private ThreadPoolTaskExecutor taskExecutor;
    @Resource
    private UserMapper userMapper;

    /**
     * 导出100W数据 Excel
     * 一个sheet 10W条数据
     * <p>
     * 单线程 100W 耗时: 17784ms 15973ms 15840ms 16313ms 15875ms
     * 多线程 100W 耗时
     */
    @GetMapping("/export")
    public void exportTest(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("测试", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        long startTime = System.currentTimeMillis();
        Long total = userMapper.selectCount(null);
        try (ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream(), User.class).build()) {
            long pageSize = 100000;
            long pages = total / pageSize;
            if (pages < 1) {
                WriteSheet writeSheet = EasyExcel.writerSheet(1, "模板" + 1).build();
                List<User> users = userMapper.selectList(null);
                excelWriter.write(users, writeSheet);
            } else {
                for (long i = 0; i < pages; i++) {
                    // 每次都要创建writeSheet 这里注意必须指定sheetNo 而且sheetName必须不一样
                    WriteSheet writeSheet = EasyExcel.writerSheet((int) i, "模板" + i).build();
                    // 分页去数据库查询数据 这里可以去数据库查询每一页的数据
                    Page<User> page = new Page<>();
                    page.setCurrent(i + 1);
                    page.setSize(pageSize);
                    Page<User> userPage = userMapper.selectPage(page, null);
                    excelWriter.write(userPage.getRecords(), writeSheet);
                }
            }
        }
        log.info("导出耗时/ms:" + (System.currentTimeMillis() - startTime) + ",导出数据总条数：" + total);
    }

    /**
     * easyExcel 不支持对单个文件的并发写入、读取
     * 此处是并发查询数据
     * 耗时： 13063ms 11218ms 13628ms 11648ms 11825ms
     * <p>
     * 其他想法：并发写到多个excel 最后合并
     * 或者使用其他工具实现多线程写入同一个文件
     */
    @ResponseBody
    @GetMapping("/export/thread")
    public String exportThreadTest() {
        long startTime = System.currentTimeMillis();
        String fileName = "F:\\testData\\data" + System.currentTimeMillis() + ".xlsx";
        int totalData = Math.toIntExact(userMapper.selectCount(null));
        int sheetSize = 100000;
        int pages = totalData / sheetSize;

        ExcelWriter excelWriter = EasyExcel.write(fileName).build();

        List<CompletableFuture<WriteBo>> futures = new ArrayList<>(pages);
        for (int i = 0; i < pages; i++) {
            int current = i + 1;
            CompletableFuture<WriteBo> future = CompletableFuture.supplyAsync(() -> {
                List<User> dataList = fetchData(current, sheetSize);
                return new WriteBo(dataList, current);
            }, taskExecutor);
            futures.add(future);
        }
        futures.forEach(c -> {
            WriteBo writeBo = c.join();
            writeDataToSheet(writeBo.getDataList(), writeBo.getIndex(), excelWriter);
        });
        excelWriter.finish();

        log.info("导出耗时/ms:" + (System.currentTimeMillis() - startTime) + ",导出数据总条数：" + totalData);
        return "success";
    }

    private List<User> fetchData(int current, int size) {
        Page<User> page = new Page<>();
        page.setCurrent(current);
        page.setSize(size);
        log.info("current = {}", current);
        Page<User> userPage = userMapper.selectPage(page, null);
        log.info(userPage.getRecords().get((int) (userPage.getSize() - 1)).getId().toString());
        return userPage.getRecords();
    }

    private void writeDataToSheet(List<User> dataList, int sheetNo, ExcelWriter excelWriter) {
        log.info("SheetNo = {}", sheetNo);
        WriteSheet writeSheet = EasyExcel.writerSheet(sheetNo, "Sheet" + sheetNo).build();
        excelWriter.write(dataList, writeSheet);
    }
}
