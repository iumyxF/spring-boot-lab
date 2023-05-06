package com.example.schedule.config;

import com.example.schedule.job.FirstJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author fzy
 * @description: 自动配置Scheduler
 * @date 2023/5/6 14:46
 */
@Configuration
public class QuartzConfig {
    private static final String ID = "SUMMER-DAY";

    /**
     * 配置FirstJob的任务详情
     */
    @Bean
    public JobDetail jobDetail1() {
        return JobBuilder.newJob(FirstJob.class)
                .withIdentity(ID + "first")
                .storeDurably()
                .build();
    }

    /**
     * 配置FirstJob的触发器
     */
    @Bean
    public Trigger trigger1() {
        // 简单的调度计划的构造器
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                // 频率
                .withIntervalInSeconds(5)
                // 次数
                .repeatForever();
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail1())
                .withIdentity(ID + "first-Trigger")
                .withSchedule(scheduleBuilder)
                .build();
    }

}
