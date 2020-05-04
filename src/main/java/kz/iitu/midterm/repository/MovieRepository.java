package kz.iitu.midterm.repository;

import kz.iitu.midterm.entity.Movie;
import kz.iitu.midterm.entity.Purchase;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface MovieRepository extends CrudRepository<Movie, Long> {
    List<Movie> findByPurchase(Purchase purchase, Sort sort);
}
