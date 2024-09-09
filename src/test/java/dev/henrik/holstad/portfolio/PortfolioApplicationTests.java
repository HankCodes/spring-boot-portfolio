package dev.henrik.holstad.portfolio;

import dev.henrik.holstad.portfolio.repositories.TicketRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PortfolioApplicationTests extends PostgresDatabaseContainer {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TicketRepository ticketRepository;

    @BeforeEach
    void setUp() {
        ticketRepository.deleteAll();
    }


    @Test
    @Transactional
    void getTicket() throws Exception {
        mockMvc.perform(get("/tickets"))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    void createTicket() throws Exception {
        mockMvc.perform(post("/tickets")
                        .contentType("application/json")
                        .content("{\"title\":\"test\",\"description\":\"test\"}"))
                .andExpect(status().isOk());
    }

}
