package kz.iitu.midterm.dao;

import kz.iitu.midterm.event.NotificationEvent;
import kz.iitu.midterm.model.Purchase;
import kz.iitu.midterm.model.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.util.List;

@Component
public class PurchaseDAO implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher eventPublisher;
    JdbcTemplate jdbcTemplate;

    private final String SQL_FIND_PURCHASE = "select * from purchases where id = ?";
    private final String SQL_GET_ALL = "select * from purchases";
    private final String SQL_INSERT = "INSERT INTO purchases(id,ticketAmount,total) VALUES(?,?,?)";

    @Autowired
    public PurchaseDAO(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Purchase getPurchaseById(int id) {
        return jdbcTemplate.queryForObject(SQL_FIND_PURCHASE, new Object[] { id }, new PurchaseMapper());
    }

    public List<Purchase> getAllPurchases() {
        return jdbcTemplate.query(SQL_GET_ALL, new PurchaseMapper());
    }

    public void update(PurchaseDAO purchase, Integer id, int amount, int total){
        jdbcTemplate.update(SQL_INSERT, id, amount, total);
        this.eventPublisher.publishEvent(new NotificationEvent(this, purchase));
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }

}
