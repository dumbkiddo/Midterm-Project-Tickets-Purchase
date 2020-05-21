package kz.iitu.midterm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kz.iitu.midterm.entity.Movie;
import kz.iitu.midterm.model.CartInfo;
import kz.iitu.midterm.model.MovieInfo;
import kz.iitu.midterm.pagination.PaginationResult;
import kz.iitu.midterm.repository.MovieRepo;
import kz.iitu.midterm.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@Transactional
@Api(value = "Movie Controller class", description = "This class allows to control the data flow of movies section")
public class MovieController {

    @Autowired
    private MovieRepo movieRepo;

    @ApiOperation(value = "Viewing list of movies on different pages ", response = String.class)
    @RequestMapping({ "/movieList" })
    public String listProductHandler(Model model, //
                                     @RequestParam(value = "name", defaultValue = "") String likeName,
                                     @RequestParam(value = "page", defaultValue = "1") int page) {
        final int maxResult = 5;
        final int maxNavigationPage = 10;

        PaginationResult<MovieInfo> result = movieRepo.queryMovies(page, //
                maxResult, maxNavigationPage, likeName);

        model.addAttribute("paginationProducts", result);
        return "movieList";
    }

    @ApiOperation(value = " View the ordered movie", response = String.class)
    @RequestMapping({ "/buyProduct" })
    public String listProductHandler(HttpServletRequest request, Model model, //
                                     @RequestParam(value = "code", defaultValue = "") String code) {

        Movie movie = null;
        if (code != null && code.length() > 0) {
            movie = movieRepo.findMovie(code);
        }
        if (movie != null) {

            //
            CartInfo cartInfo = Session.getCartInSession(request);

            MovieInfo movieInfo = new MovieInfo(movie);

            cartInfo.addMovie(movieInfo, 1);
        }

        return "redirect:/shoppingCart";
    }

    @ApiOperation(value = "Remove an order", response = String.class)
    @RequestMapping({ "/shoppingCartRemoveProduct" })
    public String removeProductHandler(HttpServletRequest request, Model model, //
                                       @RequestParam(value = "code", defaultValue = "") String code) {
        Movie movie = null;
        if (code != null && code.length() > 0) {
            movie = movieRepo.findMovie(code);
        }
        if (movie != null) {

            CartInfo cartInfo = Session.getCartInSession(request);

            MovieInfo movieInfo = new MovieInfo(movie);

            cartInfo.removeMovie(movieInfo);

        }

        return "redirect:/shoppingCart";
    }

    @ApiOperation(value = " [GET] - View of the movie image", response = String.class)
    @RequestMapping(value = { "/productImage" }, method = RequestMethod.GET)
    public void productImage(HttpServletRequest request, HttpServletResponse response, Model model,
                             @RequestParam("code") String code) throws IOException {
        Movie movie = null;
        if (code != null) {
            movie = this.movieRepo.findMovie(code);
        }
        if (movie != null && movie.getImage() != null) {
            response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
            response.getOutputStream().write(movie.getImage());
        }
        response.getOutputStream().close();
    }
}
