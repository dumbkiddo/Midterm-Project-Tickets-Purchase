package kz.iitu.midterm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kz.iitu.midterm.entity.Purchase;
import kz.iitu.midterm.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/purchase")
@Api(value = "Purchase Controller class", description = "This class is used for editing accessing and editing purchase details")
public class PurchaseController {

    @Autowired
    private PurchaseRepository repository;

    @ApiOperation(value = "Method for creating purchases")
    @PostMapping("/add")
    public Purchase addPurchase(@RequestBody  Purchase purchase) {
        return repository.save(purchase);
    }

    @ApiOperation(value = "List all made purchases")
    @GetMapping("/all")
    public Iterable<Purchase> allPurchases() {
        return repository.findAll();
    }

    @ApiOperation(value = "Find a purchase by ID")
    @GetMapping("/{id}")
    public Optional<Purchase> purchasebyId(@PathVariable("id") Long id) {
        return repository.findById(id);
    }

    @ApiOperation(value = "Edit/update purchase details")
    @PutMapping("/update")
    public Purchase updatePurchase(@RequestBody Purchase purchase) {
        return repository.save(purchase);
    }

    @ApiOperation(value = "Delete purchase by ID")
    @DeleteMapping("/{id}")
    public void deletePurchase(@PathVariable("id") Long id) {
        repository.deleteById(id);
    }
}

