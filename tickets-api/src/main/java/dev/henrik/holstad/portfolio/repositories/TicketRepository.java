package dev.henrik.holstad.portfolio.repositories;


import dev.henrik.holstad.portfolio.models.dao.Ticket;
import org.springframework.data.repository.CrudRepository;

public interface TicketRepository extends CrudRepository<Ticket, Integer> {

}
