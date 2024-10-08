package dev.henrik.holstad.portfolio.controllers;

import dev.henrik.holstad.portfolio.exceptions.ResourceNotFoundException;
import dev.henrik.holstad.portfolio.models.dao.Ticket;
import dev.henrik.holstad.portfolio.models.dao.requests.TicketRequest;
import dev.henrik.holstad.portfolio.repositories.TicketRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/tickets")
public class TicketController {
    private final TicketRepository ticketRepository;

    @Autowired
    TicketController(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;

    }
    @GetMapping()
    public @ResponseBody Iterable<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @GetMapping("/{id}")
    public @ResponseBody Ticket getTicketById(
            @PathVariable Integer id
    ) {
        return ticketRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Ticket not found"));
    }

    @PostMapping()
    public @ResponseBody Ticket addNewTicket(
            @Valid @RequestBody TicketRequest ticketRequest
    ) {

        Ticket ticket = new Ticket();
        ticket.setTitle(ticketRequest.getTitle());
        ticket.setDescription(ticketRequest.getDescription());
        ticketRepository.save(ticket);

        return ticket;
    }

}
