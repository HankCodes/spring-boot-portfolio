package dev.henrik.holstad.portfolio.longrunningtask.controllers;

import dev.henrik.holstad.portfolio.longrunningtask.exceptions.TaskServiceException;
import dev.henrik.holstad.portfolio.longrunningtask.models.enums.TaskStatus;
import dev.henrik.holstad.portfolio.longrunningtask.models.responses.TaskResponse;
import dev.henrik.holstad.portfolio.longrunningtask.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LongRunningTaskController {

    TaskService taskService;

    @Autowired
    LongRunningTaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping()
    public @ResponseBody TaskResponse create() {
        try {
            taskService.runTask();

            return new TaskResponse(TaskStatus.RUNNING, "123");
        } catch (TaskServiceException e) {
            return new TaskResponse(TaskStatus.FAILED, "123");
        }
    }

    @GetMapping("/{id}/status")
    public @ResponseBody TaskResponse getStatus(@PathVariable String id) {

        return new TaskResponse(TaskStatus.RUNNING, id);
    }
}
