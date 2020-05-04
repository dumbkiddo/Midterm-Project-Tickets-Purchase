package kz.iitu.midterm.repository;

import kz.iitu.midterm.entity.Purchase;
import org.springframework.data.repository.CrudRepository;

public interface PurchaseRepository extends CrudRepository<Purchase, Long> {
    Purchase findByDate(String date);
}
