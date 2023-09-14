package com.example.workflow;

import com.example.workflow.service.MyService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author iumyxF
 * @description:
 * @date 2023/9/14 13:49
 */
@SpringBootApplication(proxyBeanMethods = false)
public class WorkFlowApplication {
    public static void main(String[] args) {
        SpringApplication.run(WorkFlowApplication.class, args);
    }

    //@Bean
    //public CommandLineRunner init(final RepositoryService repositoryService,
    //                              final RuntimeService runtimeService,
    //                              final TaskService taskService) {
    //    return strings -> {
    //        System.out.println("Number of process definitions : " + repositoryService.createProcessDefinitionQuery().count());
    //        System.out.println("Number of tasks : " + taskService.createTaskQuery().count());
    //        //默认查询processes目录下的xml
    //        runtimeService.startProcessInstanceByKey("oneTaskProcess");
    //        System.out.println("Number of tasks after process start: " + taskService.createTaskQuery().count());
    //    };
    //}

    @Bean
    public CommandLineRunner init(final MyService myService) {
        return strings -> myService.createDemoUsers();
    }
}
