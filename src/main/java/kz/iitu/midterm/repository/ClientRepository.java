package kz.iitu.midterm.repository;

import kz.iitu.midterm.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
