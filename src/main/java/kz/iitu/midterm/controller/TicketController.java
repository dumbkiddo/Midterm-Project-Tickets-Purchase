package kz.iitu.midterm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kz.iitu.midterm.entity.Ticket;
import kz.iitu.midterm.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/ticket")
@Api(value = "Ticket Controller class", description = "This class is used for editing accessing and editing ticket details")
public class TicketController {

    @Autowired
    private TicketRepository repository;

    @ApiOperation(value = "Method for creating new ticket types")
    @PostMapping("/add")
    public Ticket addTicket(@RequestBody Ticket ticket) {
        return repository.save(ticket);
    }

    @ApiOperation(value = "List all types of tickets")
    @GetMapping("/all")
    public Iterable<Ticket> allTicket() {
        return repository.findAll();
    }

    @ApiOperation(value = "Find a ticket type by ID")
    @GetMapping("/{id}")
    public Optional<Ticket> ticketById(@PathVariable("id") int id) {
        return repository.findById(id);
    }

    @ApiOperation(value = "Edit/update ticket details")
    @PutMapping("/update")
    public Ticket updateTicket(@RequestBody Ticket ticket) {
        return repository.save(ticket);
    }

    @ApiOperation(value = "Delete ticket by ID")
    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable("id") int id) {
        repository.deleteById(id);
    }
}

