package kz.iitu.midterm.repository;

import kz.iitu.midterm.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
}
