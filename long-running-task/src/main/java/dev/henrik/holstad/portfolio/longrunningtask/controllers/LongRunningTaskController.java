package dev.henrik.holstad.portfolio.longrunningtask.controllers;

import dev.henrik.holstad.portfolio.longrunningtask.models.enums.TaskStatus;
import dev.henrik.holstad.portfolio.longrunningtask.models.responses.TaskResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LongRunningTaskController {

    @PostMapping()
    public @ResponseBody TaskResponse create() {

        return new TaskResponse(TaskStatus.RUNNING, "123");
    }

    @GetMapping("/{id}/status")
    public @ResponseBody TaskResponse getStatus(@PathVariable String id) {

        return new TaskResponse(TaskStatus.RUNNING, id);
    }
}
