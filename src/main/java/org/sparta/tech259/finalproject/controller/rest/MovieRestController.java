package org.sparta.tech259.finalproject.controller.rest;


import org.sparta.tech259.finalproject.model.entities.movies.Movie;
import org.sparta.tech259.finalproject.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MovieRestController {
    private final MovieService movieService;

    @Autowired
    public MovieRestController(MovieService movieService){
        this.movieService= movieService;
    }

    //read all
    @GetMapping("/movies")
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();

    }

    //read singular
    @GetMapping("/movie/{id}")
    public Movie getMoviesById(@PathVariable("id") String id){
        Optional<Movie> movie = movieService.getMovieById(id);
        return movie.orElse(null);
    }

    //delete
    @DeleteMapping("/movie/{id}")
    public void deleteMovie(@PathVariable String id){
        movieService.deleteMovie(id);
    }

    //add
    @PostMapping("/movie")
    public Movie addMovie(@RequestBody Movie movie){
        return movieService.createMovie(movie);
    }

    //update
    @PutMapping("/movie/{id}")
    public Movie updateMovie(@RequestBody Movie movie, @PathVariable String id){
        Optional<Movie> movieToUpdate = movieService.getMovieById(id);
        if (movieToUpdate.isPresent()) {
            return movieService.updateMovie(id, movie);
        } else {
            return null;
        }
    }
}
