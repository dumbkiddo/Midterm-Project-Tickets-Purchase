package kz.iitu.midterm.dao;

import kz.iitu.midterm.model.Ticket;
import kz.iitu.midterm.model.TicketMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.util.List;

@Component
public class TicketDAO{

    JdbcTemplate jdbcTemplate;

    private final String SQL_FIND_TICKET = "select * from tickets where id = ?";
    private final String SQL_GET_ALL = "select * from tickets";

    @Autowired
    public TicketDAO(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Ticket getTicketById(int id) {
        return jdbcTemplate.queryForObject(SQL_FIND_TICKET, new Object[] { id }, new TicketMapper());
    }

    public List<Ticket> getAllTickets() {
        return jdbcTemplate.query(SQL_GET_ALL, new TicketMapper());
    }

}