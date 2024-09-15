package dev.henrik.holstad.portfolio.longrunningtask.models.responses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.henrik.holstad.portfolio.longrunningtask.models.enums.TaskStatus;

public class TaskResponse {
    private final TaskStatus status;
    private final String id;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public TaskResponse(@JsonProperty("status") TaskStatus status, @JsonProperty("id") String id) {
        this.status = status;
        this.id = id;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public String getId() {
        return id;
    }
}
