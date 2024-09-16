package dev.henrik.holstad.portfolio.longrunningtask.exceptions;

public class TaskServiceException extends RuntimeException {
    public TaskServiceException(String message) {
        super(message);
    }
}
