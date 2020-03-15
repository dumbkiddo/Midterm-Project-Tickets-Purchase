package kz.iitu.midterm.dao;

import kz.iitu.midterm.model.Movie;
import kz.iitu.midterm.model.MovieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.util.List;

@Component
public class MovieDAO{

    JdbcTemplate jdbcTemplate;

    private final String SQL_FIND_MOVIE = "select * from movies where id = ?";
    private final String SQL_GET_ALL = "select * from movies";

    @Autowired
    public MovieDAO(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Movie getMovieById(int id) {
        return jdbcTemplate.queryForObject(SQL_FIND_MOVIE, new Object[] { id }, new MovieMapper());
    }

    public List<Movie> getAllMovies() {
        return jdbcTemplate.query(SQL_GET_ALL, new MovieMapper());
    }


}

