package dev.henrik.holstad.portfolio.longrunningtask.services;


import dev.henrik.holstad.portfolio.longrunningtask.exceptions.TaskServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    Logger logger = LoggerFactory.getLogger(TaskService.class);

    @Async
    public void runTask() {
        try {
            logger.info("Fetching data from database");
            Thread.sleep(1000);
            logger.info("Task completed step 1");

            Thread.sleep(1500);
            logger.info("Task completed step 2");

            Thread.sleep(800);
            logger.info("Task completed step 3");
            logger.info("Task done");
        } catch (InterruptedException e) {
            logger.error("Task was interrupted", e);

            throw new TaskServiceException("Task was interrupted");
        }
    }
}


