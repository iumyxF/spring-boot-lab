package com.example.workflow.controller;

import com.example.workflow.domain.dto.StartProcessRepresentation;
import com.example.workflow.domain.dto.TaskRepresentation;
import com.example.workflow.service.MyService;
import org.flowable.task.api.Task;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author iumyxF
 * @description:
 * @date 2023/9/14 14:58
 */
@RestController
public class MyRestController {

    @Resource
    private MyService myService;

    @PostMapping(value = "/process")
    public void startProcessInstance(@RequestBody StartProcessRepresentation startProcessRepresentation) {
        myService.startProcess(startProcessRepresentation.getAssignee());
    }

    @GetMapping(value = "/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TaskRepresentation> getTasks(@RequestParam String assignee) {
        List<Task> tasks = myService.getTasks(assignee);
        List<TaskRepresentation> dtos = new ArrayList<>();
        for (Task task : tasks) {
            dtos.add(new TaskRepresentation(task.getId(), task.getName()));
        }
        return dtos;
    }
}
