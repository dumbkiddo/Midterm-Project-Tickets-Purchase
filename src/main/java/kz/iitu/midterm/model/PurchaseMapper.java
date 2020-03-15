package kz.iitu.midterm.model;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PurchaseMapper implements RowMapper<Purchase> {

    public Purchase mapRow(ResultSet resultSet, int i) throws SQLException {
        Purchase purchase = new Purchase();
        purchase.setId(resultSet.getInt(1));
        purchase.setTicketAmount(resultSet.getInt(2));
        purchase.setTotal(resultSet.getInt(3));
        return purchase;
    }
}