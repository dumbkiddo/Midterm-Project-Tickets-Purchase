package kz.iitu.midterm.controller;

import kz.iitu.midterm.entity.Client;
import kz.iitu.midterm.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Optional;

@RestController
@RequestMapping(path = "/client")
@Api(value = "Client Controller class", description = "This class is used for editing accessing and editing client details")
public class ClientController {

    @Autowired
    private ClientRepository repository;

    @ApiOperation(value = "Method for adding new clients")
    @PostMapping("/add")
    public Client addClient(@RequestBody Client client) {
        return repository.save(client);
    }


    @ApiOperation(value = "List all clients from database")
    @GetMapping("/all")
    public Iterable<Client> allClient() {
        return repository.findAll();
    }

    @ApiOperation(value = "Find particular client by ID")
    @GetMapping("/find/{id}")
    public Optional<Client> clientById(@PathVariable("id") int id) {
        return repository.findById(id);
    }


    @ApiOperation(value = "Edit/update client details")
    @PutMapping("/update")
    public Client updateClient(@RequestBody Client client) {
        return repository.save(client);
    }

    @ApiOperation(value = "Delete client by ID")
    @DeleteMapping("/delete/{id}")
    public void deleteClient(@PathVariable("id") int id) {
        repository.deleteById(id);
    }
}
