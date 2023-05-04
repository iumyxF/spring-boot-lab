package com.example.schedule.tasks;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author iumyxF
 * @description: 实现SchedulingConfigurer接口
 * @date 2023/5/4 9:36
 */
@Slf4j
@Component
public class SecondTask implements SchedulingConfigurer {

    /**
     * 这种方式可以动态的修改执行时间，cron可以从数据库或者其他地方获取再创建任务对象
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        String cron = "0/5 * * * * ?";
        taskRegistrar.addTriggerTask(
                //1.添加任务内容(Runnable)
                () -> log.info("基于接口实现定时任务...: " + LocalDateTime.now().toLocalTime()),
                //2.设置执行周期(Trigger)
                triggerContext -> new CronTrigger(cron).nextExecutionTime(triggerContext)
        );
    }
}
