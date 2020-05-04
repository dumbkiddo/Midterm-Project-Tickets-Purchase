package kz.iitu.midterm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kz.iitu.midterm.entity.Movie;
import kz.iitu.midterm.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/movie")
@Api(value = "Movie Controller class", description = "This class is used for editing accessing and editing movie details")
public class MovieController {

    @Autowired
    private MovieRepository repository;


    @ApiOperation(value = "Method for adding new movies into database")
    @PostMapping("/add")
    public Movie addMovie(@RequestBody Movie movie) {
        return repository.save(movie);
    }

    @ApiOperation(value = "List all available movies ")
    @GetMapping("/all")
    public Iterable<Movie> allMovies() {
        return repository.findAll();
    }

    @ApiOperation(value = "Find particular movie by ID")
    @GetMapping("/{id}")
    public Optional<Movie> moviebyId(@PathVariable("id") Long id) {
        return repository.findById(id);
    }

    @ApiOperation(value = "Edit/update movie details")
    @PutMapping("/update")
    public Movie updateMovie(@RequestBody Movie movie) {
        return repository.save(movie);
    }

    @ApiOperation(value = "Delete movie by ID")
    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable("id") Long id) {
        repository.deleteById(id);
    }
}
