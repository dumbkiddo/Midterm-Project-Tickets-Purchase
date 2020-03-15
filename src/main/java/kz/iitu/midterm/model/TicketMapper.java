package kz.iitu.midterm.model;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TicketMapper implements RowMapper<Ticket> {

    public Ticket mapRow(ResultSet resultSet, int i) throws SQLException {
        Ticket ticket = new Ticket();
        ticket.setId(resultSet.getInt(1));
        ticket.setType(resultSet.getString(2));
        ticket.setMorningPrice(resultSet.getInt(3));
        ticket.setAfternoonPrice(resultSet.getInt(4));
        ticket.setEveningPrice(resultSet.getInt(5));
        return ticket;
    }
}