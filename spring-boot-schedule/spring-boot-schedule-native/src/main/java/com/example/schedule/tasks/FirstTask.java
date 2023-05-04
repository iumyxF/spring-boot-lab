package com.example.schedule.tasks;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author iumyxF
 * @description: 使用注解启动定时任务
 * @date 2023/5/4 9:15
 */
@Slf4j
@Component
public class FirstTask {

    @Scheduled(cron = "0/10 * * * * ?")
    public void firstScheduleTask() {
        log.info("注解执行定时任务...");
    }
}
