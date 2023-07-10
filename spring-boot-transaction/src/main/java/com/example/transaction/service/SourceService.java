package com.example.transaction.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.transaction.domain.Source;

/**
 * @author iumyxF
 * @description 针对表【tr_source】的数据库操作Service
 * @createDate 2023-07-10 09:15:25
 */
public interface SourceService extends IService<Source> {

    /**
     * 测试业务方法
     * @param source
     */
    void insertErrorLog(Source source);

    /**
     * 测试业方法
     */
    void doSomething();
}
