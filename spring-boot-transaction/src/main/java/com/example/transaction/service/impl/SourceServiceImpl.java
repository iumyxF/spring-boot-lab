package com.example.transaction.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.transaction.domain.Source;
import com.example.transaction.mapper.SourceMapper;
import com.example.transaction.service.SourceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * @author iumyxF
 * @description 针对表【tr_source】的数据库操作Service实现
 * @createDate 2023-07-10 09:15:25
 */
@Service
public class SourceServiceImpl extends ServiceImpl<SourceMapper, Source> implements SourceService {

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertErrorLog(Source source) {
        this.save(source);
    }

    @Override
    public void doSomething() {
        System.out.println("SourceService doSomething....");
        ArrayList<Source> list = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            Source source = new Source("test_" + i + 1);
            list.add(source);
        }
        this.saveBatch(list);
        int i = 10 / 0;
    }
}




