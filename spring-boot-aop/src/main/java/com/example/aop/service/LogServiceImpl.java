package com.example.aop.service;

import cn.hutool.core.bean.BeanUtil;
import com.example.aop.entities.OperationLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @description: 操作日志处理
 * @Date 2023/2/13 10:57
 * @author iumyxF
 */
@Slf4j
@Service
public class LogServiceImpl {
    @Async
    @EventListener
    public void recordOperationLog(OperationLog logEvent) {
        OperationLog operationLog = BeanUtil.toBean(logEvent, OperationLog.class);
        log.info("异步监听到的log对象{}", operationLog);
    }

}
