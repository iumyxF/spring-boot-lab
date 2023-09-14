package com.example.workflow.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.workflow.domain.Person;
import com.example.workflow.mapper.PersonMapper;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author iumyxF
 * @description:
 * @date 2023/9/14 14:57
 */
@Service
public class MyService {

    @Resource
    private RuntimeService runtimeService;
    @Resource
    private TaskService taskService;
    @Resource
    private PersonMapper personMapper;

    @Transactional(rollbackFor = Exception.class)
    public void startProcess() {
        runtimeService.startProcessInstanceByKey("oneTaskProcess");
    }

    @Transactional(rollbackFor = Exception.class)
    public List<Task> getTasks(String assignee) {
        return taskService.createTaskQuery().taskAssignee(assignee).list();
    }

    public void startProcess(String assignee) {
        Person person = personMapper.findByUsername(assignee);
        Map<String, Object> variables = new HashMap<>(16);
        variables.put("person", person);
        runtimeService.startProcessInstanceByKey("oneTaskProcess", variables);
    }

    public void createDemoUsers() {
        Long count = personMapper.selectCount(new QueryWrapper<>());
        if (null == count || count == 0) {
            personMapper.insert(new Person("jbarrez", "Joram", "Barrez", new Date()));
            personMapper.insert(new Person("trademakers", "Tijs", "Rademakers", new Date()));
        }
    }
}
