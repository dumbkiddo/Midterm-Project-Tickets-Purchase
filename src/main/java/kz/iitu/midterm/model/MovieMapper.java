package kz.iitu.midterm.model;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieMapper implements RowMapper<Movie> {

    public Movie mapRow(ResultSet resultSet, int i) throws SQLException {
        Movie movie = new Movie();
        movie.setId(resultSet.getInt(1));
        movie.setTitle(resultSet.getString(2));
        movie.setMorningTime(resultSet.getString(3));
        movie.setAfternoonTime(resultSet.getString(4));
        movie.setEveningTime(resultSet.getString(5));
        return movie;
    }
}
