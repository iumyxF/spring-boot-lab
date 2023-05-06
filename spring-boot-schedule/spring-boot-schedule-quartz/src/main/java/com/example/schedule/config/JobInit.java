package com.example.schedule.config;

import com.example.schedule.job.SecondJob;
import org.quartz.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * @author fzy
 * @description: 手动配置Scheduler
 * @date 2023/5/6 14:49
 */
@Component
public class JobInit implements ApplicationRunner {

    private static final String ID = "SUMMER-DAY";

    @Resource
    private Scheduler scheduler;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        JobDetail jobDetail = JobBuilder.newJob(SecondJob.class)
                .withIdentity(ID + "second")
                .storeDurably()
                .build();
        CronScheduleBuilder scheduleBuilder =
                CronScheduleBuilder.cronSchedule("0/10 * * * * ? *");
        // 创建任务触发器
        Trigger trigger = TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(ID + "secondTrigger")
                .withSchedule(scheduleBuilder)
                .startNow() //立即執行一次任務
                .build();
        Set<Trigger> set = new HashSet<>();
        set.add(trigger);
        // boolean replace 表示启动时对数据库中的quartz的任务进行覆盖。
        scheduler.scheduleJob(jobDetail, set, true);
    }
}
