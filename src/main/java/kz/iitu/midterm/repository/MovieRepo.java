package kz.iitu.midterm.repository;

import kz.iitu.midterm.entity.Movie;
import kz.iitu.midterm.form.MovieForm;
import kz.iitu.midterm.model.MovieInfo;
import kz.iitu.midterm.pagination.PaginationResult;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.io.IOException;
import java.util.Date;

@Transactional
@Repository
public class MovieRepo {

    @Autowired
    private SessionFactory sessionFactory;

    public Movie findMovie(String code) {
        try {
            String sql = "Select e from " + Movie.class.getName() + " e Where e.code =:code ";

            Session session = this.sessionFactory.getCurrentSession();
            Query<Movie> query = session.createQuery(sql, Movie.class);
            query.setParameter("code", code);
            return (Movie) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public MovieInfo findMovieInfo(String code) {
        Movie movie = this.findMovie(code);
        if (movie == null) {
            return null;
        }
        return new MovieInfo(movie.getCode(), movie.getName(), movie.getPrice());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void save(MovieForm movieForm) {

        Session session = this.sessionFactory.getCurrentSession();
        String code = movieForm.getCode();

        Movie movie = null;

        boolean isNew = false;
        if (code != null) {
            movie = this.findMovie(code);
        }
        if (movie == null) {
            isNew = true;
            movie = new Movie();
            movie.setCreateDate(new Date());
        }
        movie.setCode(code);
        movie.setName(movieForm.getName());
        movie.setPrice(movieForm.getPrice());

        if (movieForm.getFileData() != null) {
            byte[] image = null;
            try {
                image = movieForm.getFileData().getBytes();
            } catch (IOException e) {
            }
            if (image != null && image.length > 0) {
                movie.setImage(image);
            }
        }
        if (isNew) {
            session.persist(movie);
        }
        session.flush();
    }

    public PaginationResult<MovieInfo> queryMovies(int page, int maxResult, int maxNavigationPage,
                                                   String likeName) {
        String sql = "Select new " + MovieInfo.class.getName() //
                + "(p.code, p.name, p.price) " + " from "//
                + Movie.class.getName() + " p ";
        if (likeName != null && likeName.length() > 0) {
            sql += " Where lower(p.name) like :likeName ";
        }
        sql += " order by p.createDate desc ";
        //
        Session session = this.sessionFactory.getCurrentSession();
        Query<MovieInfo> query = session.createQuery(sql, MovieInfo.class);

        if (likeName != null && likeName.length() > 0) {
            query.setParameter("likeName", "%" + likeName.toLowerCase() + "%");
        }
        return new PaginationResult<MovieInfo>(query, page, maxResult, maxNavigationPage);
    }

    public PaginationResult<MovieInfo> queryMovies(int page, int maxResult, int maxNavigationPage) {
        return queryMovies(page, maxResult, maxNavigationPage, null);
    }

}