package dev.henrik.holstad.portfolio.longrunningtask.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.henrik.holstad.portfolio.longrunningtask.models.enums.TaskStatus;
import dev.henrik.holstad.portfolio.longrunningtask.models.responses.TaskResponse;
import dev.holstad.henrik.test.PostgresDatabaseContainer;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class LongRunningTaskTests extends PostgresDatabaseContainer {
    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;


    @Test
    @Transactional
    void createTask_onSuccess_returnsTaskWithStatusRunning() throws Exception {
        TaskStatus expected = TaskStatus.RUNNING;

        String json = mockMvc.perform(post("/"))
                .andReturn()
                .getResponse()
                .getContentAsString();
        TaskResponse task = objectMapper.readValue(json, TaskResponse.class);

        TaskStatus actual = task.getStatus();
        assertEquals(expected, actual);
    }
}
