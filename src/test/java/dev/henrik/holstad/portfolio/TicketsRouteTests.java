package dev.henrik.holstad.portfolio;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.henrik.holstad.portfolio.models.dao.Ticket;
import dev.henrik.holstad.portfolio.models.dao.responses.ErrorResponse;
import dev.henrik.holstad.portfolio.repositories.TicketRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class TicketsRouteTests extends PostgresDatabaseContainer {
    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TicketRepository ticketRepository;

    @Test
    @Transactional
    void createTicket_withTitleAndDescription_returnsSuccess() throws Exception {
        String expectedTitle = "test";
        String expectedDescription = "test";

        String json = mockMvc.perform(post("/tickets")
                        .contentType("application/json")
                        .content(String.format("{\"title\":\"%s\",\"description\":\"%s\"}", expectedTitle, expectedDescription)))
                .andReturn()
                .getResponse()
                .getContentAsString();
        Ticket ticket = new ObjectMapper().readValue(json, Ticket.class);

        assertEquals(expectedTitle, ticket.getTitle());
        assertEquals(expectedDescription, ticket.getDescription());
    }

    @Test
    @Transactional
    void createTicket_withEmptyDescription_returnsSuccess() throws Exception {
        String expectedTitle = "test";
        String expectedDescription = "";

        String json = mockMvc.perform(post("/tickets")
                        .contentType("application/json")
                        .content(String.format("{\"title\":\"%s\",\"description\":\"%s\"}", expectedTitle, expectedDescription)))
                .andReturn()
                .getResponse()
                .getContentAsString();
        Ticket ticket = new ObjectMapper().readValue(json, Ticket.class);

        assertEquals(expectedTitle, ticket.getTitle());
        assertEquals(expectedDescription, ticket.getDescription());
    }

    @Test
    @Transactional
    void createTicket_tooLongTitle_givesHttp400Response() throws Exception {
        Integer expected = 400;
        String title = "a".repeat(51);

        String json = mockMvc.perform(post("/tickets")
                        .contentType("application/json")
                        .content(String.format("{\"title\":\"%s\"}", title)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        Integer actual = objectMapper.readValue(json, ErrorResponse.class).getStatus();

        assertEquals(expected, actual);
    }

    @Test
    @Transactional
    void createTicket_tooShortTitle_givesHttp400Response() throws Exception {
        Integer expected = 400;

        String json = mockMvc.perform(post("/tickets")
                        .contentType("application/json")
                        .content("{\"title\":\"\"}"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        Integer actual = objectMapper.readValue(json, ErrorResponse.class).getStatus();

        assertEquals(expected, actual);
    }

    @Test
    @Transactional
    void createTicket_missingTitle_givesHttp400Response() throws Exception {
        Integer expected = 400;

        String json = mockMvc.perform(post("/tickets")
                        .contentType("application/json")
                        .content("{\"description\":\"desc\"}"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        Integer actual = objectMapper.readValue(json, ErrorResponse.class).getStatus();

        assertEquals(expected, actual);
    }

    @Test
    @Transactional
    void createTicket_tooLongDescription_givesHttp400Response() throws Exception {
        Integer expected = 400;
        String description = "a".repeat(501);

        String json = mockMvc.perform(post("/tickets")
                        .contentType("application/json")
                        .content(String.format("{\"title\":\"title\", \"description\": \"%s\"}", description)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        Integer actual = objectMapper.readValue(json, ErrorResponse.class).getStatus();

        assertEquals(expected, actual);
    }

    @Test
    @Transactional
    void getTickets_noAvailableTickets_givesEmptyList() throws Exception {
        Integer expected = 0;

        String json = mockMvc.perform(get("/tickets"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        Integer actual = objectMapper.readValue(json, Ticket[].class).length;
        assertEquals(expected, actual);
    }

    @Test
    @Transactional
    void getTickets_hasAvailableTickets_givesListOfTickets() throws Exception {
        Integer expected = 2;

        for (int i = 0; i < 2; i++) {
            Ticket ticket = new Ticket();
            ticket.setTitle("test");
            ticketRepository.save(ticket);
        }

        String json = mockMvc.perform(get("/tickets"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        Integer actual = objectMapper.readValue(json, Ticket[].class).length;
        assertEquals(expected, actual);
    }

    @Test
    @Transactional
    void getTicketById_ticketExists_givesTicket() throws Exception {
        Ticket ticket = new Ticket();
        ticket.setTitle("test");
        Ticket createdTicket = ticketRepository.save(ticket);

        String json = mockMvc.perform(get("/tickets/" + createdTicket.getId()))
                .andReturn()
                .getResponse()
                .getContentAsString();

        Ticket actual = objectMapper.readValue(json, Ticket.class);
        assertEquals(ticket.getId(), actual.getId());
    }

    @Test
    @Transactional
    void getTicketById_ticketDoNotExist_givesHttp400Error() throws Exception {
        mockMvc.perform(get("/tickets/1"))
                .andExpect(result -> assertEquals(404, result.getResponse().getStatus()));
    }
}
